package contacrf.model.test;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.Test;

import contacrf.model.ContaCorrente;

public class ContaTest {

	public class ContaCorrenteTest extends TestCase {
		private ContaCorrente instance;
		private ArrayList contas;

		@Test
		protected void setUp() {
			contas = new ArrayList();
			instance = new ContaCorrente();
		}

		public void testDeposita() {

			double result = instance.setSaldo(100.0);
			//instance.deposita(100.0);
			System.out.println(instance.getSaldo());
			assertEquals(instance.getSaldo(), 200.0);
			assertFalse(instance);
			assertTrue(instance);
			assertNotNull(instance);
			// assertNull(a);
			// assertNotSame(a, b);
			// assertSame(a, b);
			// fail();
		}

		protected void tearDown() {
			contas.clear();
		}

		public void testSaca() {

			double result = instance.setSaldo(200.0);
			//instance.sacar(100.0);
			System.out.println(instance.getSaldo());
			assertEquals(instance.getSaldo(), 100.0);
			assertFalse(instance);
			assertTrue(instance);
			assertNotNull(instance);
			// assertNull(a);
			// assertNotSame(a, b);
			// assertSame(a, b);
			// fail();
		}

		private void assertFalse(ContaCorrente instance2) {
			// TODO Auto-generated method stub

		}

		private void assertTrue(ContaCorrente instance2) {
			// TODO Auto-generated method stub

		}

		public void testTranferi() {

			double result = instance.setSaldo(200.0);
			//instance.sacar(100.0);
			System.out.println(instance.getSaldo());
			assertEquals(instance.getSaldo(), 100.0);
			assertFalse(instance);
			assertTrue(instance);
			assertNotNull(instance);
			// assertNull(a);
			// assertNotSame(a, b);
			// assertSame(a, b);
			// fail();
		}

	}
}
