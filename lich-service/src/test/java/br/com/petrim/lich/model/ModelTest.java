package br.com.petrim.lich.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

public class ModelTest {
	
	private static Validator accessValidator;
	
	@BeforeClass
	public static void init() {
		accessValidator = ValidatorBuilder.create()
					.with(new GetterTester())
					.with(new SetterTester())
				.build();
	}
	
	@Test
	public void testModels() {
		List<PojoClass> classes = new ArrayList<>();
		
		classes.add(PojoClassFactory.getPojoClass(Agent.class));
		classes.add(PojoClassFactory.getPojoClass(Audit.class));
		classes.add(PojoClassFactory.getPojoClass(Functionality.class));
		classes.add(PojoClassFactory.getPojoClass(Group.class));
		classes.add(PojoClassFactory.getPojoClass(JobProcess.class));
		classes.add(PojoClassFactory.getPojoClass(JobProtocol.class));
		classes.add(PojoClassFactory.getPojoClass(Parameter.class));
		classes.add(PojoClassFactory.getPojoClass(StepProcess.class));
		classes.add(PojoClassFactory.getPojoClass(StepProtocol.class));
		classes.add(PojoClassFactory.getPojoClass(User.class));
		
		accessValidator.validate(classes);
	}
	
}
