package net.karneim.pojobuilder;


import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;

import net.karneim.pojobuilder.BuilderModelProducer;
import net.karneim.pojobuilder.Input;
import net.karneim.pojobuilder.Output;
import net.karneim.pojobuilder.TypeMUtils;
import net.karneim.pojobuilder.model.BuilderM;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import testdata.generics.NumberGrid;
import testenv.AddToSourceTree;
import testenv.ProcessingEnvironmentRunner;

@RunWith(ProcessingEnvironmentRunner.class)
@AddToSourceTree({ TestData.SRC_TESTDATA_DIR })
public class GenericsTest extends TestBase {

	private ProcessingEnvironment env;

	private BuilderModelProducer underTest;

	@Before
	public void setup() {
		env = ProcessingEnvironmentRunner.getProcessingEnvironment();
		TypeMUtils typeMUtils = new TypeMUtils();
		underTest = new BuilderModelProducer(env, typeMUtils);
	}

	@Test
	public void testProduceModelReturnsModelWithTypeParameters() {
		// Given:
		String pojoClassname = NumberGrid.class.getName();
		TypeElement pojoTypeElement = env.getElementUtils().getTypeElement(pojoClassname);

		// When:
		Output output = underTest.produce(new Input(pojoTypeElement));
		BuilderM builder = output.getBuilder();

		// Then:
		assertEquals("type", "NumberGridBuilder<E extends Number>", builder.getType()
				.getGenericTypeSimpleNameWithBounds());

	}

}
