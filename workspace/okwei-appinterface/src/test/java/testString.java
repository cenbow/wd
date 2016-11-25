import static org.junit.Assert.*;

import org.junit.Test;


public class testString {

	@Test
	public void test() {
		String imgUrl="/////////////////////////base/mimg.jpg";
		imgUrl = imgUrl.replace("//", "/");	
		System.out.print(imgUrl);
	}

}
