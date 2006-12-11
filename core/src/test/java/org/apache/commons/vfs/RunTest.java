/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.vfs;

import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestResult;
import org.apache.commons.vfs.provider.http.test.HttpProviderTestCase;
import org.apache.commons.vfs.provider.jar.test.JarProviderTestCase;
import org.apache.commons.vfs.provider.jar.test.NestedJarTestCase;
import org.apache.commons.vfs.provider.sftp.test.SftpProviderTestCase;
import org.apache.commons.vfs.provider.tar.test.NestedTarTestCase;
import org.apache.commons.vfs.provider.tar.test.NestedTbz2TestCase;
import org.apache.commons.vfs.provider.tar.test.NestedTgzTestCase;
import org.apache.commons.vfs.provider.tar.test.TarProviderTestCase;
import org.apache.commons.vfs.provider.tar.test.Tbz2ProviderTestCase;
import org.apache.commons.vfs.provider.tar.test.TgzProviderTestCase;
import org.apache.commons.vfs.provider.zip.test.NestedZipTestCase;
import org.apache.commons.vfs.provider.zip.test.ZipProviderTestCase;
import org.apache.commons.vfs.provider.ftp.test.FtpProviderTestCase;
import org.apache.commons.vfs.provider.local.test.LocalProviderTestCase;
import org.apache.commons.vfs.provider.res.test.ResourceProviderTestCase;
import org.apache.commons.vfs.provider.temp.test.TemporaryProviderTestCase;
import org.apache.commons.vfs.provider.url.test.UrlProviderTestCase;
import org.apache.commons.vfs.provider.url.test.UrlProviderHttpTestCase;
import org.apache.commons.vfs.provider.test.VirtualProviderTestCase;
import org.apache.commons.vfs.provider.test.GenericFileNameTestCase;

import java.util.Properties;

public class RunTest
{
	public static void main(String[] args) throws Exception
	{
		Properties props = System.getProperties();
		props.setProperty("test.data.src", "src/test-data");
		props.setProperty("test.basedir", "core/target/test-data");
		props.setProperty("test.policy", "src/test-data/test.policy");
		props.setProperty("test.secure", "false");
		props.setProperty("test.smb.uri",
				"smb://HOME\\vfsusr:vfs%2f%25\\te:st@172.16.110.129/vfsusr/vfstest");
		props.setProperty("test.ftp.uri",
				"ftp://vfsusr:vfs%2f%25\\te:st@172.16.110.129/vfstest");
		props.setProperty("test.http.uri", "http://172.16.110.129/vfstest");
		props.setProperty("test.webdav.uri",
				"webdav://vfsusr:vfs%2f%25\\te:st@172.16.110.129/vfstest");
		props.setProperty("test.sftp.uri",
				"sftp://vfsusr:vfs%2f%25\\te:st@172.16.110.129/vfstest");

		Test tests[] = new Test[]
		{
		// SmbProviderTestCase.suite(),

		// LocalProviderTestCase.suite(),
			
		FtpProviderTestCase.suite(),

		UrlProviderHttpTestCase.suite(),

		VirtualProviderTestCase.suite(),
		TemporaryProviderTestCase.suite(),
		UrlProviderTestCase.suite(),
		// ResourceProviderTestCase.suite(),

		HttpProviderTestCase.suite(),

		// WebdavProviderTestCase.suite(),

		SftpProviderTestCase.suite(),

		JarProviderTestCase.suite(),
		NestedJarTestCase.suite(),
		ZipProviderTestCase.suite(),
		NestedZipTestCase.suite(),
		TarProviderTestCase.suite(),
		TgzProviderTestCase.suite(),
		Tbz2ProviderTestCase.suite(),
		NestedTarTestCase.suite(),
		NestedTgzTestCase.suite(),
		NestedTbz2TestCase.suite(),
		};

		TestResult result = new TestResult()
		{
			public void startTest(Test test)
			{
				System.out.println("start " + test);
				System.out.flush();
			}

			public void endTest(Test test)
			{
				// System.err.println("end " + test);
			}

			public synchronized void addError(Test test, Throwable throwable)
			{
				// throw new RuntimeException(throwable.getMessage());
				throwable.printStackTrace();
			}

			public synchronized void addFailure(Test test,
					AssertionFailedError assertionFailedError)
			{
				// throw new RuntimeException(assertionFailedError.getMessage());
				assertionFailedError.printStackTrace();
			}
		};

		for (int i = 0; i < tests.length; i++)
		{
			System.out.println("start test#" + i);
			System.out.flush();

			Test test = tests[i];
			test.run(result);

			// break;
		}
	}
}
