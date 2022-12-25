import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class OperadoresTest {
    @Test
    public void subscriberOnSimple(){
        // por default, tudo é executado na main thread
        // subscribeOn(<Schedulers>) -> define a quantidade e a thread do flux
        // Schedulers.single() ou Schedulers.boundedElastic() -> thread unica
        Flux<Integer> flux = Flux
                .range(1,4)
                .map(numero -> {
                    log.info("MAP-1: "+Thread.currentThread().getName());
                    return numero;
                })
                .subscribeOn(Schedulers.boundedElastic())
                .map(numero -> {
                    log.info("MAP-2: "+Thread.currentThread().getName());
                    return numero;
                });

        StepVerifier.create(flux)
                .expectSubscription()
                .expectNext(1,2,3,4)
                .verifyComplete();
    }

    @Test
    public void publishOnSimple(){
        /*
        publishOn(<Schedulers>):
            -> define a quantidade e a thread do flux, a partir do metodo, ou seja, so afeta o que vem posteriormente a sua chamada
            -> é capaz de sobreescrever/transpor/substituir os metodos:
                - subscribeOn() -> antes ou depois da chamada do publishOn()
                - publishOn() -> executado anteriormente
        */
        Flux<Integer> flux = Flux
                .range(1,4)
                .map(numero -> {
                    log.info("MAP-1: "+Thread.currentThread().getName());
                    return numero;
                })
                .publishOn(Schedulers.boundedElastic())
                .map(numero -> {
                    log.info("MAP-2: "+Thread.currentThread().getName());
                    return numero;
                });

        StepVerifier.create(flux)
                .expectSubscription()
                .expectNext(1,2,3,4)
                .verifyComplete();
    }

    @Test
    public void multipleSubscriberOnSimple(){
        // CASO: sao utilizados multiplos subscribeOn(), mas somente o primeiro ira determinar a thread
        Flux<Integer> flux = Flux
                .range(1,4)
                .subscribeOn(Schedulers.single())
                .map(numero -> {
                    log.info("MAP-1: "+Thread.currentThread().getName());
                    return numero;
                })
                .subscribeOn(Schedulers.boundedElastic())
                .map(numero -> {
                    log.info("MAP-2: "+Thread.currentThread().getName());
                    return numero;
                });

        StepVerifier.create(flux)
                .expectSubscription()
                .expectNext(1,2,3,4)
                .verifyComplete();
    }

    @Test
    public void multiplePublishOnSimple(){
        // CASO: sao utilizados multiplos publishOn(), onde sao executados até que outro o sobreescreva/transposição de thread
        Flux<Integer> flux = Flux
                .range(1,4)
                .publishOn(Schedulers.single())
                .map(numero -> {
                    log.info("MAP-1: "+Thread.currentThread().getName());
                    return numero;
                })
                .publishOn(Schedulers.boundedElastic())
                .map(numero -> {
                    log.info("MAP-2: "+Thread.currentThread().getName());
                    return numero;
                });

        StepVerifier.create(flux)
                .expectSubscription()
                .expectNext(1,2,3,4)
                .verifyComplete();
    }

    @Test
    public void publishAndSubscribeOnSimple(){
        // CASO: publishOn() sobreescreve/transposição o subscribeOn(), praticamente ignorando a sua instrução
        Flux<Integer> flux = Flux
                .range(1,4)
                .publishOn(Schedulers.single())
                .map(numero -> {
                    log.info("MAP-1: "+Thread.currentThread().getName());
                    return numero;
                })
                .subscribeOn(Schedulers.boundedElastic())
                .map(numero -> {
                    log.info("MAP-2: "+Thread.currentThread().getName());
                    return numero;
                });

        StepVerifier.create(flux)
                .expectSubscription()
                .expectNext(1,2,3,4)
                .verifyComplete();
    }

    @Test
    public void SubscribeAndPublishOnSimple(){
        // CASO: publishOn() sobreescreve/transposição o subscribeOn()
        Flux<Integer> flux = Flux
                .range(1,4)
                .subscribeOn(Schedulers.boundedElastic())
                .map(numero -> {
                    log.info("MAP-1: "+Thread.currentThread().getName());
                    return numero;
                })
                .publishOn(Schedulers.single())
                .map(numero -> {
                    log.info("MAP-2: "+Thread.currentThread().getName());
                    return numero;
                });

        StepVerifier.create(flux)
                .expectSubscription()
                .expectNext(1,2,3,4)
                .verifyComplete();
    }

    @Test
    public void subscribeOnIO() throws Exception {
        // fromCallable() -> executa em thread background, recomendado em operações de chamadas externas
        Mono<List<String>> arquivo = Mono
                .fromCallable(() -> Files.readAllLines(Path.of("text-file")))
                .log()
                .subscribeOn(Schedulers.boundedElastic());

        arquivo.subscribe(
                strings -> log.info(strings.toString())
        );

        // Thread.sleep(300);

        StepVerifier.create(arquivo)
                .expectSubscription()
                .thenConsumeWhile(lista -> {
                    Assertions.assertFalse(lista.isEmpty());
                    return true;
                })
                .verifyComplete();
    }

    private Flux<Object> emptyFlux(){
        return Flux.empty();
    }

    @Test
    public void switchIfEmptyOperator(){
        // switchIfEmpty(<valor_se_vazio>) -> se o Flux nao possuir valor, ele verifica e atribui
        Flux<Object> flux = emptyFlux()
                .switchIfEmpty(Flux.just("VAZIO JAMAIS"))
                .log();

        flux.subscribe(
                valor -> log.info(valor.toString())
        );

        StepVerifier.create(flux)
                .expectNext("VAZIO JAMAIS")
                .verifyComplete();
    }

    @Test
    public void deferOperator() throws Exception{
        // defer() -> atualiza o valor a cada subscribe
        Mono<Long> defer = Mono.defer(
                () -> Mono.just(System.currentTimeMillis())
        ) ;

        Thread.sleep(100);
        defer.subscribe(valor -> log.info(valor.toString()));

        Thread.sleep(100);
        defer.subscribe(valor -> log.info(valor.toString()));

        Thread.sleep(100);
        defer.subscribe(valor -> log.info(valor.toString()));

        AtomicLong atomicLong = new AtomicLong();
        defer.subscribe(atomicLong::set);
        Assertions.assertTrue(atomicLong.get() > 0);
    }

    @Test
    public void concatOperador(){
        // concat() -> junta os valores e retorna um novo Flux, executando de forma LAZY(um flux por vez e sequencialmente)
        Flux<String> flux1 = Flux.just("a", "b");
        Flux<String> flux2 = Flux.just("c", "d");

        Flux<String> fluxConcat = Flux
                .concat(flux1, flux2)
                .log();

        StepVerifier.create(fluxConcat)
                .expectNext("a", "b", "c", "d")
                .verifyComplete();
    }

    @Test
    public void concatDelayErrorOperador(){
        // concatDelayError() -> semelahnte ao concat(), porem em caso de exception continua a execução
        Flux<String> flux1 = Flux
                .just("a", "b")
                .map(s -> {
                    if(s.equals("b"))
                        throw new IllegalArgumentException();
                    return s;
                });
        Flux<String> flux2 = Flux.just("c", "d");

        Flux<String> fluxConcat = Flux
                .concatDelayError(flux1, flux2)
                .log();

        StepVerifier.create(fluxConcat)
                .expectNext("a", "c", "d")
                .expectError()
                .verify();
    }

    @Test
    public void concatWhithOperador(){
        // concatWith() -> adiciona os valores de um Flux á um já existente
        Flux<String> flux1 = Flux.just("a", "b");
        Flux<String> flux2 = Flux.just("c", "d");

        Flux<String> fluxConcat = flux1.concatWith(flux2).log();

        StepVerifier.create(fluxConcat)
                .expectNext("a", "b", "c", "d")
                .verifyComplete();
    }

    @Test
    public void combineLatestOperador(){
        // combineLatest(<flux's>, (<referenciaElementoFluxRespectivo>) -> logica) ->
        //  combina os ultimos elementos, dado o tempo de execução, ou seja, se o flux1(a,b) demorar mais que o flux2(c,d) => (bc, bd)
        Flux<String> flux1 = Flux.just("a", "b");
        Flux<String> flux2 = Flux.just("c", "d");

        Flux<String> fluxConcat = Flux
                .combineLatest(flux1, flux2,
                        (s1, s2) -> s1.toUpperCase() + s2.toUpperCase()
                ).log();

        fluxConcat.subscribe(log::info);
    }

    @Test
    public void mergeOperador() throws Exception {
        // merge() -> semelhante ao concat(), mas executa de forma EAGER(atribui os valores pela disposição e não pela sequencia)
        // ou seja, "quem chegar primeiro pega os primeiros lugares"
        Flux<String> flux1 = Flux.just("a", "b").delayElements(Duration.ofMillis(200));
        Flux<String> flux2 = Flux.just("c", "d");

        Flux<String> fluxMerge = Flux.merge(flux1, flux2).log();

        fluxMerge.subscribe(log::info);

        Thread.sleep(1000);

        StepVerifier.create(fluxMerge)
                .expectNext("c", "d", "a", "b")
                .verifyComplete();
    }

    @Test
    public void mergeWithOperador() throws Exception {
        // mergeWith() -> semelhante ao concatWith(), mas com a execução do merge()
        Flux<String> flux1 = Flux.just("a", "b").delayElements(Duration.ofMillis(200));
        Flux<String> flux2 = Flux.just("c", "d");

        Flux<String> fluxMerge = flux1.mergeWith(flux2).log();

        fluxMerge.subscribe(log::info);

        Thread.sleep(1000);

        StepVerifier.create(fluxMerge)
                .expectNext("c", "d", "a", "b")
                .verifyComplete();
    }

    @Test
    public void mergeSequentialOperador() {
        // mergeSequential() -> junta os valores e retorna um novo Flux, executando de forma LAZY(sequencial), semelhante ao concat()
        Flux<String> flux1 = Flux.just("a", "b").delayElements(Duration.ofMillis(200));
        Flux<String> flux2 = Flux.just("c", "d");

        Flux<String> fluxMerge = Flux
                .mergeSequential(flux1, flux2)
                .delayElements(Duration.ofMillis(100))
                .log();

        fluxMerge.subscribe(log::info);

        StepVerifier.create(fluxMerge)
                .expectNext("a", "b", "c", "d")
                .verifyComplete();
    }

    @Test
    public void mergeDelayErrorOperador() {
        // mergeDelayError() -> semelhante ao concatDelayError(), só que no contexto de merge.
        // doOnError() -> consome o erro e interrompe o fluxo
        Flux<String> flux1 = Flux
                .just("a", "b")
                .map(s -> {
                    if(s.equals("b"))
                        throw new IllegalArgumentException();
                    return s;
                })
                .doOnError(t -> log.error(t.getMessage()))
                .delayElements(Duration.ofMillis(200));

        Flux<String> flux2 = Flux.just("c", "d");

        Flux<String> fluxMerge = Flux
                .mergeDelayError(1, flux1, flux2)
                .delayElements(Duration.ofMillis(100))
                .log();

        fluxMerge.subscribe(log::info);
    }

    @Test
    public void flatMapOperator() {
        // flatMap() -> aplica uma funcao ao valor, achatando a sua resultante, em execução EAGER
        // map() -> retornou um flux de flux de strings, o flatMap() retornou um flux de strings
        Flux<String> flux1 = Flux.just("A", "B");

        Flux<Flux<String>> flux2 = flux1
                .map(String::toUpperCase)
                .map(this::findByName)
                .log();

        flux2.subscribe(valor -> log.info(valor.toString()));

        Flux<String> flux3 = flux1
                .map(String::toUpperCase)
                .flatMap(this::findByName)
                .log();

        flux3.subscribe(log::info);
    }

    @Test
    public void flatMapSequencialOperator() {
        // flatMapSequencial() -> semelhante ao flatMap(), com execução LAZY sequencial
        // flatMapSequencialDelayError()
        Flux<String> flux1 = Flux.just("A", "B");

        Flux<String> flux2 = flux1
                .map(String::toUpperCase)
                .flatMapSequential(this::findByName)
                .log();

        flux2.subscribe(log::info);
    }

    private Flux<String> findByName(String nome) {
        return nome.equals("A") ? Flux.just("NOME_A1", "NOME_A2") : Flux.just("NOME_B1", "NOME_B2");
    }

    @AllArgsConstructor
    @Data
    static class Animal {
        private String nome, cor, raca;
    }

    @Test
    public void zipOperator() {
        // zip() -> junta os valores de FLux até o momento onde é correspondido, ou seja, segue o limite do menor flux.
        // zipWith() -> semelhante ao zip(), mas só suporta 2 argumentos.
        Flux<String> nome = Flux.just("animal_1", "animal_2", "animal_3");
        Flux<String> cor = Flux.just("preto", "marrom", "branco");
        Flux<String> raca = Flux.just("raca_1", "raca_2", "raca_3");

        // Flux<Tuple[quantidade_elementos_join]<[tipos_respectivos]>>
        Flux<Animal> animalFlux = Flux
                .zip(nome, cor, raca)
                .flatMap(tupla ->
                        Flux.just(new Animal(tupla.getT1(), tupla.getT2(), tupla.getT3()))
                );

        animalFlux.subscribe(valor -> log.info(valor.toString()));
    }
}
