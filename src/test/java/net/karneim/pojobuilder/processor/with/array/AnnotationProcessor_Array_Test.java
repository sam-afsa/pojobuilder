package net.karneim.pojobuilder.processor.with.array;

import net.karneim.pojobuilder.processor.AnnotationProcessor;
import net.karneim.pojobuilder.processor.with.ProcessorTestSupport;
import net.karneim.pojobuilder.testenv.JavaProject.Compilation;
import org.junit.Test;

import static net.karneim.pojobuilder.PbAssertions.assertThat;

/**
 * @feature The {@link AnnotationProcessor} generates builder classes.
 */
public class AnnotationProcessor_Array_Test extends ProcessorTestSupport {

  /**
   * @throws Exception
   * @scenario the builder is created with appropriate array properties.
   */
  @Test
  public void testShouldGenerateBuilderWithArrayProperties() throws Exception {
    // Given:
    sourceFor(Pojo.class);
    // When:
    boolean success = prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(PojoBuilder.class)
        .compiled(PojoBuilder.class)
        .reported(Compilation.Success);

  }

  /**
   * @throws Exception
   * @scenario the builder is created with appropriate generic array properties.
   */
  @Test
  public void testShouldGenerateBuilderWithGenericArrayProperties() throws Exception {
    // Given:
    sourceFor(GenericPojo.class);
    // When:
    boolean success = prj.compile();
    // Then:
    assertThat(prj)
        .generatedSameSourceAs(GenericPojoBuilder.class)
        .compiled(GenericPojoBuilder.class)
        .reported(Compilation.Success);
  }
}
