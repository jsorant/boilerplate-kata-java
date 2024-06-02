package com.jsorant.kata.wardrobe;

import static org.assertj.core.api.Assertions.assertThat;

import com.jsorant.kata.UnitTest;
import java.util.List;
import org.junit.jupiter.api.Test;

@UnitTest
public class WardRobeTest {

  @Test
  void shouldHaveNoCombinationsIfNoElements() {
    int wallWidth = 0;
    List<Element> elements = List.of();
    WardRobe wardRobe = new WardRobe(wallWidth, elements);

    assertThat(wardRobe.combinations().values()).isEmpty();
  }

  @Test
  void shouldHaveNoCombinationsIfNothingFitWall() {
    int wallWidth = 0;
    List<Element> elements = List.of(Element.of(50));
    WardRobe wardRobe = new WardRobe(wallWidth, elements);

    assertThat(wardRobe.combinations().values()).isEmpty();
  }

  @Test
  void shouldHaveNoCombinationsIfNothingFitWallExactly() {
    int wallWidth = 60;
    List<Element> elements = List.of(Element.of(50));
    WardRobe wardRobe = new WardRobe(wallWidth, elements);

    assertThat(wardRobe.combinations().values()).isEmpty();
  }

  @Test
  void shouldFitWallOf100WithElementsOf50() {
    int wallWidth = 100;
    List<Element> elements = List.of(Element.of(50));
    WardRobe wardRobe = new WardRobe(wallWidth, elements);

    assertThat(wardRobe.combinations().values()).containsExactlyInAnyOrder(Combination.of(List.of(Element.of(50), Element.of(50))));
  }

  @Test
  void shouldFitWallOf100WithElementsOf100() {
    int wallWidth = 100;
    List<Element> elements = List.of(Element.of(100));
    WardRobe wardRobe = new WardRobe(wallWidth, elements);

    assertThat(wardRobe.combinations().values()).containsExactlyInAnyOrder(Combination.of(List.of(Element.of(100))));
  }

  @Test
  void shouldFitWallOf100WithElementsOf50And100() {
    int wallWidth = 100;
    List<Element> elements = List.of(Element.of(50), Element.of(100));
    WardRobe wardRobe = new WardRobe(wallWidth, elements);

    assertThat(wardRobe.combinations().values())
      .containsExactlyInAnyOrder(Combination.of(List.of(Element.of(50), Element.of(50))), Combination.of(List.of(Element.of(100))));
  }

  @Test
  void shouldFitWallOf150WithElementsOf50And100() {
    int wallWidth = 150;
    List<Element> elements = List.of(Element.of(50), Element.of(100));
    WardRobe wardRobe = new WardRobe(wallWidth, elements);

    assertThat(wardRobe.combinations().values())
      .containsExactlyInAnyOrder(
        Combination.of(List.of(Element.of(50), Element.of(50), Element.of(50))),
        Combination.of(List.of(Element.of(50), Element.of(100)))
      );
  }

  @Test
  void shouldFitWallOf200WithElementsOf50And100() {
    int wallWidth = 200;
    List<Element> elements = List.of(Element.of(50), Element.of(100));
    WardRobe wardRobe = new WardRobe(wallWidth, elements);

    assertThat(wardRobe.combinations().values())
      .containsExactlyInAnyOrder(
        Combination.of(List.of(Element.of(50), Element.of(50), Element.of(50), Element.of(50))),
        Combination.of(List.of(Element.of(50), Element.of(50), Element.of(100))),
        Combination.of(List.of(Element.of(100), Element.of(100)))
      );
  }

  @Test
  void shouldFitWallOf250WithElementsOf50_75_100And120() {
    int wallWidth = 250;
    List<Element> elements = List.of(Element.of(50), Element.of(75), Element.of(100), Element.of(120));
    WardRobe wardRobe = new WardRobe(wallWidth, elements);

    assertThat(wardRobe.combinations().values())
      .containsExactlyInAnyOrder(
        Combination.of(List.of(Element.of(50), Element.of(50), Element.of(50), Element.of(50), Element.of(50))),
        Combination.of(List.of(Element.of(50), Element.of(50), Element.of(50), Element.of(100))),
        Combination.of(List.of(Element.of(50), Element.of(100), Element.of(100))),
        Combination.of(List.of(Element.of(75), Element.of(75), Element.of(100))),
        Combination.of(List.of(Element.of(50), Element.of(50), Element.of(75), Element.of(75)))
      );
  }
}
