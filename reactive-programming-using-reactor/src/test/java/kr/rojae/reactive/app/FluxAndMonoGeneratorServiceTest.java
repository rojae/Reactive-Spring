package kr.rojae.reactive.app;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.List;

public class FluxAndMonoGeneratorServiceTest {

	FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

	@Test
	void nameFlux(){
		// given

		// when
		var namesFlux = fluxAndMonoGeneratorService.namesFlux();

		// then
		// StepVerifier.create :: publisher와 subscriber사이의 event를 trigger 시킨다.
		StepVerifier.create(namesFlux)
				//.expectNext("rojae", "kim", "alex")
				.expectNextCount(3)
				.verifyComplete();
	}

	@Test
	void namesFlux_map() {
		// given

		// when
		var namesFluxMap= fluxAndMonoGeneratorService.namesFlux_map();

		// then
		StepVerifier.create(namesFluxMap)
				.expectNext("ROJAE", "KIM", "ALEX")
				.verifyComplete();
	}

	@Test
	void namesFlux_immutability() {
		// given

		// when
		// namesFlux_map()와 비교하여, 내부 동작을 확인
		var namesFluxMap= fluxAndMonoGeneratorService.namesFlux_immutability();

		// then
		StepVerifier.create(namesFluxMap)
				.expectNext("rojae", "kim", "alex")
				.verifyComplete();
	}

	@Test
	void testNamesFlux_map() {
		// given
		int stringLength = 3;

		// when
		var nameFlux = fluxAndMonoGeneratorService.namesFlux_map(stringLength);

		// then
		StepVerifier.create(nameFlux)
				//.expectNext("ROJAE", "ALEX")
				.expectNext("5-ROJAE", "4-ALEX")
				.verifyComplete();
	}

	@Test
	void namesFlux_flatMap() {
		// given
		int stringLength = 3;

		// when
		var nameFLux = fluxAndMonoGeneratorService.namesFlux_flatMap(stringLength);

		// then
		StepVerifier.create(nameFLux)
				.expectNext("R", "O", "J", "A", "E", "A", "L", "E", "X")
				.verifyComplete();
	}

	@Test
	void namesFlux_flatMap_async() {
		// given
		int stringLength = 3;

		// when
		var nameFLux = fluxAndMonoGeneratorService.namesFlux_flatMap_async(stringLength);

		// then
		StepVerifier.create(nameFLux)
//				.expectNext("R", "O", "J", "A", "E", "A", "L", "E", "X")
				.expectNextCount(9)
				.verifyComplete();
	}

	@Test
	void namesFlux_concatMap_async() {
		// given
		int stringLength = 3;

		// when
		var nameFLux = fluxAndMonoGeneratorService.namesFlux_concatMap_async(stringLength);

		// then
		StepVerifier.create(nameFLux)
				.expectNext("R", "O", "J", "A", "E", "A", "L", "E", "X")
				.verifyComplete();
	}

	@Test
	void namesMono_map_filter() {
		// given
		int stringLength = 3;

		// when
		var value = fluxAndMonoGeneratorService.namesMono_map_filter(stringLength);

		// then
		StepVerifier.create(value)
				.expectNext("ALEX")
				.verifyComplete();
	}

	@Test
	void namesMono_map_flatMap() {
		// given
		int stringLength = 3;

		// when
		var value = fluxAndMonoGeneratorService.namesMono_map_flatMap(stringLength);

		// then
		StepVerifier.create(value)
				.expectNext(List.of("A", "L", "E", "X"))
				.verifyComplete();
	}

	@Test
	void namesMono_map_flatMapMany() {
		// given
		int stringLength = 3;

		// when
		var value = fluxAndMonoGeneratorService.namesMono_map_flatMapMany(stringLength);

		// then
		StepVerifier.create(value)
				.expectNext("A", "L", "E", "X")
				.verifyComplete();
	}

	@Test
	void namesFlux_transform() {
		// given
		int stringLength = 3;

		// when
		var value = fluxAndMonoGeneratorService.namesFlux_transform(stringLength);

		// then
		StepVerifier.create(value)
				.expectNextCount(9)
				.verifyComplete();
	}

	@Test
	void namesFlux_transform_defaultIfEmpty() {
		// given
		int stringLength = 6;

		// when
		var value = fluxAndMonoGeneratorService.namesFlux_transform(stringLength);

		// then
		StepVerifier.create(value)
				.expectNext("default")
				.verifyComplete();
	}

	@Test
	void namesFlux_transform_switchIfEmpty() {
		// given
		int stringLength = 6;

		// when
		var value = fluxAndMonoGeneratorService.namesFlux_transform_switchIfEmpty(stringLength);

		// then
		StepVerifier.create(value)
				.expectNext("DEFAULT")
				.verifyComplete();
	}

	@Test
	void explore_concat() {
		// given

		// when
		var value = fluxAndMonoGeneratorService.explore_concat();

		// then
		StepVerifier.create(value)
				.expectNext("A", "B", "C", "D", "E", "F")
				.verifyComplete();
	}

	@Test
	void explore_concatWith() {
		// given

		// when
		var value = fluxAndMonoGeneratorService.explore_concatWith();

		// then
		StepVerifier.create(value)
				.expectNext("D", "E", "F", "D", "E", "F")
				.verifyComplete();
	}

	@Test
	void explore_concatWith_mono() {
		// given

		// when
		var value = fluxAndMonoGeneratorService.explore_concatWith_mono();

		// then
		StepVerifier.create(value)
				.expectNext("A", "B")
				.verifyComplete();
	}

	@Test
	void explore_merge() {
		// given

		// when
		var value = fluxAndMonoGeneratorService.explore_merge();

		// then
		StepVerifier.create(value)
				.expectNext("A", "D", "B", "E", "C", "F")
				.verifyComplete();
	}

	@Test
	void explore_mergeWith() {
		// given

		// when
		var value = fluxAndMonoGeneratorService.explore_mergeWith();

		// then
		StepVerifier.create(value)
				.expectNext("A", "D", "B", "E", "C", "F")
				.verifyComplete();
	}

	@Test
	void explore_mergeSequential() {
		// given

		// when
		var value = fluxAndMonoGeneratorService.explore_mergeSequential();

		// then
		StepVerifier.create(value)
				.expectNext("A", "B", "C", "D", "E", "F")
				.verifyComplete();
	}

	@Test
	void explore_zip() {
		// given

		// when
		var value = fluxAndMonoGeneratorService.explore_zip();

		// then
		StepVerifier.create(value)
				.expectNext("AD", "BE", "CF")
				.verifyComplete();
	}

	@Test
	void explore_zipWith() {
		// given

		// when
		var value = fluxAndMonoGeneratorService.explore_zipWith();

		// then
		StepVerifier.create(value)
				.expectNext("AD", "BE", "CF")
				.verifyComplete();
	}

	@Test
	void explore_zipWith_mono() {
		// given

		// when
		var value = fluxAndMonoGeneratorService.explore_zipWith_mono();

		// then
		StepVerifier.create(value)
				.expectNext("AB")
				.verifyComplete();
	}

	@Test
	void explore_zip_1() {
		// given

		// when
		var value = fluxAndMonoGeneratorService.explore_zip_1();

		// then
		StepVerifier.create(value)
				.expectNext("AD14", "BE25", "CF36")
				.verifyComplete();
	}


}