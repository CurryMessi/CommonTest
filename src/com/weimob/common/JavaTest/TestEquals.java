package com.weimob.common.JavaTest;

public class TestEquals {
	
	public static void main(String[] args) {
		//== 比较的是对象是否一直，equals比较的是内容是否一致（值）
		
		String a = new String("ab"); // a 为一个引用
		String b = new String("ab"); // b为另一个引用,对象的内容一样
		String aa = "ab"; // 放在常量池中
		String bb = "ab"; // 从常量池中查找
		 // true  ab放入方法区常量池中，bb也指向同一个对象
		if (aa == bb)
			System.out.println("aa==bb");
		// false，a和b不是同一对象，new了两次创建了两个
		if (a == b) 
			System.out.println("a==b");
		 // true 重写了Object类中的equals
		if (a.equals(b))
			System.out.println("aEQb");
//		if (42 == 42.0) { // true
//			System.out.println("true");
//		}
		String a1=new String("abc");
		String b1="abc";
		//True 重写了Object类中的equals，object中的equals就是==，比较的是对象地址，则为false ab为两个对象
		System.out.println(a1.equals(b1));
		System.out.println(new TestEquals().toString());
	}

}
