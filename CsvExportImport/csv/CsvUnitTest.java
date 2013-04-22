import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;

public class CsvUnitTest {
	
	String input = "field1,field2,field3,field4\n"
					+"This,is,a,test\n"
					+"This,is,\"one more\",test\n"
					+"This,is also,\"one\nmore\",test\n"
					+"ã“ã‚“ã«ã¡ã¯,Ð·Ð´Ñ€Ð°Ð²ÐµÐ¹,\"àª¹à«‡àª²à«àª²à«‹\",hello\n"
					+"\"\"\"another\"\"\",\" edge \", case, \"here\" \n";
	
	String[][] output = {{"field1", "field2", "field3", "field4"},
						{"This", "is", "a", "test"},
						{"This", "is", "one more", "test"},
						{"This", "is also", "one\nmore", "test"},
						{ "ã“ã‚“ã«ã¡ã¯", "Ð·Ð´Ñ€Ð°Ð²ÐµÐ¹", "àª¹à«‡àª²à«àª²à«‹","hello" },
						{"\"another\""," edge "," case","here"}};
	
	String cannonical = "field1,field2,field3,field4\n"
			+"This,is,a,test\n"
			+"This,is,one more,test\n"
			+"This,is also,\"one\nmore\",test\n"
			+"ã“ã‚“ã«ã¡ã¯,Ð·Ð´Ñ€Ð°Ð²ÐµÐ¹,àª¹à«‡àª²à«àª²à«‹,hello\n"
			+"\"\"\"another\"\"\", edge , case,here\n";


	//@Test
	public void test() throws Exception {
		InputStream sis = new ByteArrayInputStream(input.getBytes("UTF-8"));
		ByteArrayOutputStream sos = new ByteArrayOutputStream();
		
		CsvInputStream cis = new CsvInputStream(sis);
		cis.setEncoding("UTF-8");
		cis.setDelimiter(',');
		
		CsvOutputStream cos = new CsvOutputStream(sos);
		cos.setEncoding("UTF-8");
		cos.setDelimiter(',');
		
		String []row;
		int rowIndex = 0;
		while((row=cis.readLine()) != null) {
			String[] expected = output[rowIndex];
			System.out.println(row);
			//Assert.assertArrayEquals(expected, row);
			cos.writeLine(row);
			rowIndex++;
		}
		//Assert.assertTrue(rowIndex == output.length);
		cis.close();
		cos.close();
		
		 System.out.println(new String(sos.toByteArray(),"UTF-8"));
		//Assert.assertEquals(new String(sos.toByteArray(),"UTF-8"),cannonical);
	}

}