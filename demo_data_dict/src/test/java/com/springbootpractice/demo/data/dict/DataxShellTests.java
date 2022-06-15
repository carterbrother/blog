package com.springbootpractice.demo.data.dict;

import com.springbootpractice.demo.data.dict.util.ShellExecutorUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

//@SpringBootTest
@Slf4j
class DataxShellTests {



	@BeforeEach
	void setUp() {
		log.info("开始测试线程池执行脚本：{}",ShellExecutorUtil.class);
	}



	@Test
	void testExecuteShell() {

		Future<AtomicInteger> future = ShellExecutorUtil.executeShell("python /tmp/datax/bin/datax.py /tmp/2020_05_14_15_54_24/35_base_security_client.json", true);

		AtomicInteger exitValue = new AtomicInteger(100);
		try {
			exitValue = future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		Assert.assertTrue("脚本执行失败",exitValue.get()==0);

	}


	@Test
	void testListFile() {
		Arrays.stream(Objects.requireNonNull(new File("/tmp/dataXScript").listFiles((dir, name) -> name.endsWith(".json"))))
				.map(File::getAbsolutePath)
				.sorted(((o1, o2) -> {
					String o1Number = o1.substring(o1.lastIndexOf("/")+1,o1.indexOf("_"));
					String o2Number = o2.substring(o2.lastIndexOf("/")+1,o2.indexOf("_"));
					return Integer.parseInt(o1Number) - Integer.parseInt(o2Number);
				}))
				.forEachOrdered(scriptName -> {
					String shellCmd = String.format("python /tmp/datax/bin/datax.py %s", scriptName);
					System.out.println(shellCmd);
				});
	}

	@AfterEach
	void tearDown() {
		ShellExecutorUtil.closePool();
	}
}
