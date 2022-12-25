import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.blockhound.BlockHound;
import reactor.blockhound.BlockingOperationError;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/*
    -> backpressure - limitação de objetos(request)
    -> Mono(0 ou 1 valor) e Flux(N valores) -> Publisher com tipagem Data Stream

    - Publisher <- (subscribe) Subscriber
    - Subscription é criado
    - Publisher (onSubscribe) -> Subscriber
    - Subscription <- (request N) Subscriber
    - Publisher -> (onNext) Subscriber

    Funcionamento:
        -> Publisher envia somente os objetos solicitados.
        -> Publisher envia todos os objetos possiveis(onComplete) -> cancela o subscriber e o subscription.
        -> Em caso de erro, o (onError) é chamado -> cancela o subscriber e o subscription.

    Metodos:
        - onSubscribe()
        - request(<elementos_requisitados>)
        - onNext(<valorSaida>) -> saida
        - onComplete() -> completado
*/

@Slf4j
public class MonoTest {
    public String nomeTeste = "Kaique";

    @BeforeAll
    public static void setUp(){
        // BlockHound.install();
        BlockHound.install(builder -> {
            // allowBlockingCallsInside(<classe>, <nomeMetodo>) -> bloqueia metodos especificos para evitar a interferencia nas threads
            builder.allowBlockingCallsInside("", "");
        });
    }

    @Test
    public void blockHoundWorks() {
        // verifica se existem threds bloqueadas
        try{
            FutureTask<?> task = new FutureTask<>(() -> {
                Thread.sleep(0);
                return "";
            });

            Schedulers.parallel().schedule(task);
            task.get(10, TimeUnit.SECONDS);

            Assertions.fail("SAIU");
        } catch (Exception e) {
            Assertions.assertTrue(e.getCause() instanceof BlockingOperationError);
        }
    }

    @Test
    public void monoSubscriber(){
        Mono<String> mono = Mono.just(nomeTeste).log();
        // log() -> a sequencia de execução interna, geralmente chamado apos a a tribuição de valor. Bloqueia a thread
        // empty() -> esvazia o valor contido no Mono

        mono.subscribe(log::info);

        log.info("------------------------------------");

        // StepVerifier -> implementação de verificação de Publisher
        // expectNext(<data>) -> saida esperada
        // verifyComplete() -> verifica se alcançou a conclusão de processamento
        StepVerifier
                .create(mono)
                .expectNext("Kaique")
                .verifyComplete();
    }

    @Test
    public void monoSubscriberConsumer(){
        // subscribe(<consumerSucess>) -> ate o subscribe, nada acontece
        Mono
                .just("Kaique")
                .log()
                .subscribe(
                        sucesso -> log.info(sucesso)
                );
    }

    @Test
    public void monoSubscriberConsumerError(){
        // subscribe(<consumerSucess>, <consumerError>)
        // map() -> aplica uma alteração/função no valor
        Mono<String> mono =
                Mono
                        .just(nomeTeste)
                        .map(erro -> {
                            throw new RuntimeException("Teste Mono Erro");
                        });

        mono
                .subscribe(
                        sucesso -> log.info(sucesso),
                        erro -> log.error("ERRO!")
                );

        mono
                .log()
                .subscribe(
                        sucesso -> log.info(sucesso),
                        Throwable::printStackTrace
                );

        log.info("------------------------------------");

        // expectError(<exception>.class) -> declara uma exception esperada
        // verifyComplete() -> verifica se foi completado(pode substuir o expectComplete)
        StepVerifier
                .create(mono)
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    public void monoSubscriberConsumerComplete(){
        // subscribe(<consumerSucess>, <consumerError>, <consumerComplete>)
        Mono
                .just("Kaique")
                .log()
                .map(String::toUpperCase)
                .subscribe(
                        sucesso -> log.info(sucesso),
                        erro -> erro.printStackTrace(),
                        () -> log.info("COMPLETO")
                );
    }

    @Test
    public void monoSubscriberConsumerSubscription(){
        // subscribe(<consumerSucess>, <consumerError>, <consumerComplete>, <consumerSubscription>)
        Mono
                .just("Kaique")
                .log()
                .map(String::toUpperCase)
                .subscribe(
                        sucesso -> log.info(sucesso),
                        erro -> erro.printStackTrace(),
                        () -> log.info("COMPLETO"),
                        Subscription::cancel
                );

        Mono
                .just("Kaique")
                .log()
                .map(String::toUpperCase)
                .subscribe(
                        sucesso -> log.info(sucesso),
                        erro -> erro.printStackTrace(),
                        () -> log.info("COMPLETO"),
                        subscription -> {
                            subscription.request(1); // por default o request nao declarado realiza o unbounded, ou seja, solicita todos os valores
                        }
                );
    }

    @Test
    public void monoDoOnMetodos(){
        /*
        doOn:
            -> estrutura: doOn<metodo>
            -> definição: metodos acionados dado um evento na execução do fluxo

            -> metodos:
                - doOnRequest() -> quando realizar uma request
                - doOnSubscription() -> quando realizar um subscription
                - doOnNext() -> quando realizar uma saida
                - doOnSucess() -> quando concluir todas as saidas
                - doOnError() -> quando ocorrer um erro/exception ele para a execução, nao executando o next e sucess
        */
        Mono
                .just("Kaique")
                .log()
                .map(String::toUpperCase)
                .doOnSubscribe(subscription -> log.info("SUBSCRIPTION"))
                .doOnRequest(request -> log.info("REQUEST"))
                .doOnNext(next -> log.info("NEXT"))
                .doOnSuccess(sucesso -> log.info("SUCESSO"))
                .doOnError(Throwable::printStackTrace)
                .subscribe(
                        sucesso -> log.info(sucesso),
                        erro -> erro.printStackTrace(),
                        () -> log.info("COMPLETO")
                );
    }

    @Test
    public void monoDoOnError(){
        Mono<Object> erro = Mono
                .error(new IllegalArgumentException())
                .doOnError(e -> MonoTest.log.error(e.getMessage()))
                .log();

        StepVerifier
                .create(erro)
                .expectError(IllegalArgumentException.class)
                .verify();
    }

    @Test
    public void monoOnErrorResume(){
        // onErrorResume() -> em caso de erro, continua a execução, esperando um novo valor
        // -> se for executado antes do doOnError(), ele "conserta" o erro
        Mono<Object> erro = Mono
                .error(new IllegalArgumentException())
                .doOnError(e -> MonoTest.log.error(e.getMessage()))
                .onErrorResume(s -> Mono.just(nomeTeste+"2"))
                .log();

        StepVerifier
                .create(erro)
                .expectNext(nomeTeste+"2")
                .verifyComplete();
    }

    @Test
    public void monoOnErrorReturn(){
        // onErrorReturn() -> em caso de erro, ele ignora o que vem depois(semelhante a leitura do return), esperando um valor de retorno
        Mono<Object> erro = Mono
                .error(new IllegalArgumentException())
                .onErrorReturn("VAZIO")
                .onErrorResume(s -> Mono.just(nomeTeste+"2"))
                .doOnError(e -> MonoTest.log.error(e.getMessage()))
                .log();

        StepVerifier
                .create(erro)
                .expectNext("VAZIO")
                .verifyComplete();
    }
}
