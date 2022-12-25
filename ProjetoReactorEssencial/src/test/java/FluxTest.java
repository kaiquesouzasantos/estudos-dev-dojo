import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.blockhound.BlockHound;
import reactor.blockhound.BlockingOperationError;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

@Slf4j
public class FluxTest {
    @Test
    public void fluxSubscriber(){
        Flux<String> flux = Flux
                .just("Kaique1", "Kaique2", "Kaique3", "Kaique3")
                .log();

        StepVerifier.create(flux)
                .expectNext("Kaique1", "Kaique2", "Kaique3", "Kaique3")
                .verifyComplete();
    }

    @Test
    public void fluxSubscriberNumbers(){
        // range(<inicio>, <fim>) -> gera um intervalo de valores(array)
        Flux<Integer> flux = Flux
                .range(1, 5)
                .log();

        flux.subscribe(numero -> log.info(numero.toString()));

        StepVerifier.create(flux)
                .expectNext(1,2,3,4,5)
                .verifyComplete();
    }

    @Test
    public void fluxSubscriberList(){
        // fromIterable(<list>) -> recebe implementações List...
        Flux<Integer> flux = Flux
                .fromIterable(List.of(1,2,3,4,5))
                .log();

        flux.subscribe(numero -> log.info(numero.toString()));

        StepVerifier.create(flux)
                .expectNext(1,2,3,4,5)
                .verifyComplete();
    }

    @Test
    public void fluxSubscriberNumberError(){
        Flux<Integer> flux = Flux
                .range(1,5)
                .log()
                .map(numero -> {
                    if(numero == 4)
                        throw new IndexOutOfBoundsException();
                    return numero;
                });

        flux.subscribe(
                numero -> log.info(numero.toString()),
                Throwable::printStackTrace,
                () -> log.info("COMPLETO"),
                subscription -> subscription.request(3)
        );

        StepVerifier.create(flux)
                .expectNext(1,2,3)
                .expectError(IndexOutOfBoundsException.class)
                .verify();
    }

    @Test
    public void fluxSubscriberNumberUglyBackpressure(){
        Flux<Integer> flux = Flux
                .range(1,10)
                .log();

        flux.subscribe(
                /*
                    LOGICA:
                        flux -> subscribe() -> onSubscribe() [request(2) void] -> onNext() 2x -> request(2)
                */
                new Subscriber<>() {
                    private int cont = 0;
                    private Subscription subscription;

                    @Override
                    public void onSubscribe(Subscription subscription) {
                        this.subscription = subscription;
                        subscription.request(2);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        cont++;

                        if(cont >= 2) {
                            cont = 0;
                            subscription.request(2);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {}

                    @Override
                    public void onComplete() {}
                }
        );

        StepVerifier.create(flux)
                .expectNext(1,2,3,4,5,6,7,8,9,10)
                .verifyComplete();
    }

    @Test
    public void fluxSubscriberNumberNotSoUglyBackpressure(){
        Flux<Integer> flux = Flux
                .range(1,10)
                .log();

        flux.subscribe(
                // implementação com mesma logica de fluxSubscriberNumberUglyBackpressure()
                new BaseSubscriber<>() {
                    private int cont = 0;
                    private final int requesCont = 2;

                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(requesCont);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        cont++;

                        if(cont >= requesCont) {
                            cont = 0;
                            request(requesCont);
                        }
                    }
                }
        );

        StepVerifier.create(flux)
                .expectNext(1,2,3,4,5,6,7,8,9,10)
                .verifyComplete();
    }

    @Test
    public void fluxSubscriberPrettyBackpressure(){
        // implementação com mesma logica de fluxSubscriberNumberUglyBackpressure() e fluxSubscriberNumberNotSoUglyBackpressure()
        // limitRate(<limite>) -> ele cria um pack de elementos para a request, ou seja, uma requestSimples vai receber N elementos definidas
        Flux<Integer> flux = Flux
                .range(1, 10)
                .log()
                .limitRate(2);

        flux.subscribe(numero -> log.info(numero.toString()));

        StepVerifier.create(flux)
                .expectNext(1,2,3,4,5,6,7,8,9,10)
                .verifyComplete();
    }

    @Test
    public void fluxSubscriberIntervalOne() throws Exception {
        // interval(<tempo>) -> define o intervalo de publicação
        // take(<limite>) -> define a quantidade limite de elementos necessarios
        Flux<Long> intervalo = Flux
                .interval(Duration.ofMillis(100))
                .take(10)
                .log();

        intervalo.subscribe(i -> log.info(i.toString()));

        // limita o tempo de execução
        Thread.sleep(3000);
    }

    @Test
    public void fluxSubscriberIntervalTwo() {
        /*
        withVirtualTime(<flux_interval>) -> ele realiza o teste, dado um flux contido somente em um metodo
        expectNoEvent(<tempo>) -> verifica se nada esta sendo executado antes do evento/tempo
        expectSubscription() -> espera o Subscription
        expectComplete() -> espera que complete a operação
        thenAwait(<tempo_duracao>) -> tempo de espera
        thenCancel() -> finaliza o teste
        */
        StepVerifier
                .withVirtualTime(this::createInterval)
                .expectSubscription()
                .expectNoEvent(Duration.ofHours(24))
                .thenAwait(Duration.ofDays(1))
                .expectNext(0L)
                .thenCancel()
                .verify();
    }

    private Flux<Long> createInterval(){
        return Flux.interval(Duration.ofDays(1)).log();
    }

    private ConnectableFlux<Integer> connectableFluxComponente(){
        // publish() -> habilita o envio de eventos, sem a necessidade de subscribe(hot event)
        // delayElements(<tempo>) -> delay entre cada elemento
        // FUNCIONAMENTO: apos iniciado, ele para somente apos publicar seus elementos, dado o intervalo de tempo
        return Flux
                .range(1,10)
                .log()
                .delayElements(Duration.ofMillis(100))
                .publish();
    }

    @Test
    public void connectableFlux() throws Exception {
        // connect() -> realiza o start das publicações na main thread
        ConnectableFlux<Integer> connectableFlux = connectableFluxComponente();
        connectableFlux.connect();

        Thread.sleep(300);
        // apos 300ms, o numero ira ser 3-4, dado o tempo de 3-4 publicações
        connectableFlux.subscribe(numero -> log.info("SUB-1: "+numero.toString()));

        Thread.sleep(300);
        connectableFlux.subscribe(numero -> log.info("SUB-2: "+numero.toString()));
    }

    @Test
    public void connectableFluxTeste() {
        // then(<flux_hot>::connect) -> chama o metodo de inicialização
        // thenConsumeWhile(<predicate/condicao>) -> ignora os elementos que atendem a condicao([...]enquanto for falso...faça)
        ConnectableFlux<Integer> connectableFlux = connectableFluxComponente();

        StepVerifier.create(connectableFlux)
                .then(connectableFlux::connect)
                .thenConsumeWhile(i -> i <= 5)
                .expectNext(6,7,8,9,10)
                .expectComplete()
                .verify();
    }

    @Test
    public void connectableFluxAutoConnect() {
        // autoConnect(<minimo_subscribers>) -> dispensa o uso do connect(), mas depende da chamadas do subscribe
        Flux<Integer> connectableFlux = connectableFluxComponente()
                .autoConnect(2);

        StepVerifier.create(connectableFlux)
                .then(connectableFlux::subscribe)
                .expectNext(1,2,3,4,5,6,7,8,9,10)
                .verifyComplete();
    }
}
