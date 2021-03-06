package progen.roles;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import progen.context.ProGenContext;
import progen.roles.distributed.DistributedFactory;
import progen.roles.standalone.StandaloneFactory;

public class ProGenFactoryTest {

  @Before
  public void setUp() {
    ProGenContext.makeInstance();
  }

  @After
  public void tearDown() {
    ProGenContext.clearContext();
    ProGenFactory factory = ProGenFactory.makeInstance();
    Field factoryField;
    try {
      factoryField = ProGenFactory.class.getDeclaredField("factory");
      factoryField.setAccessible(true);
      factoryField.set(factory, null);
    } catch (SecurityException e) {
      fail(e.getMessage());
    } catch (IllegalArgumentException e) {
      fail(e.getMessage());
    } catch (IllegalAccessException e) {
      fail(e.getMessage());
    } catch (NoSuchFieldException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void testMakeInstanceDefault() {
    ProGenFactory factory = ProGenFactory.makeInstance();
    assertNotNull(factory);
    assertTrue(factory instanceof StandaloneFactory);
  }

  @Test
  public void testMakeInstanceStandAlone() {
    ProGenContext.setProperty("progen.role.factory", "standalone");
    ProGenFactory factory = ProGenFactory.makeInstance();
    assertNotNull(factory);
    assertTrue(factory instanceof StandaloneFactory);
  }

  @Test
  public void testMakeInstanceDistributed() {
    ProGenContext.setProperty("progen.roles.factory", "distributed");
    ProGenFactory factory = ProGenFactory.makeInstance();
    assertNotNull(factory);
    assertTrue(factory instanceof DistributedFactory);
  }

  @Test(expected = FactoryNotFoundException.class)@Ignore
  public void testMakeInstanceUnkown() {
    ProGenContext.setProperty("progen.roles.factory", "other");
    ProGenFactory.makeInstance();
  }

}
