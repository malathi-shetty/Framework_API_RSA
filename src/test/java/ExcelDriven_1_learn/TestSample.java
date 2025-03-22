package ExcelDriven_1_learn;

import java.io.IOException;
import java.util.ArrayList;

public class TestSample {

	public static void main(String[] args) throws IOException {

		//DataDriven_2 d = new DataDriven_2();
			DataDriven_3 d = new DataDriven_3();
		ArrayList data = d.getData("Add Profile");

		System.out.println(data.get(0));
		System.out.println(data.get(1));
		System.out.println(data.get(2));
		System.out.println(data.get(3));
		System.out.println(data.get(4));

	}

}
