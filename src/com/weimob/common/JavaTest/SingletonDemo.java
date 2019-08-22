package com.weimob.common.JavaTest;

//一般情况下，不建议使用第 1 种和第 2 种懒汉方式，建议使用第 3 种饿汉方式。只有在要明确实现 lazy loading 效果时，才会使用第 5 种登记方式。如果涉及到反序列化创建对象时，
//可以尝试使用第 6 种枚举方式。如果有其他特殊的需求，可以考虑使用第 4 种双检锁方式。
public class SingletonDemo {

	// 1.单例模式 (懒汉式,线程不安全--->获取对象时才创建)
	private static SingletonDemo instance;

	// 让构造函数为 private，这样该类就不会被实例化 不能new这个对象只能用静态方法getInstance获取
	private SingletonDemo() {
	}

	// 获取唯一可用的对象
	public static SingletonDemo getInstance() {
		if (instance == null) {
			instance = new SingletonDemo();
		}
		return instance;
	}
}

class SingletonDemo2 {
	// 2.懒汉式 线程安全(加了synchronized)
	// 这种方式具备很好的 lazy loading，能够在多线程中很好的工作，但是，效率很低，99% 情况下不需要同步。
	// 优点：第一次调用才初始化，避免内存浪费 缺点：必须加锁 synchronized 才能保证单例，但加锁会影响效率
	private static SingletonDemo2 instance;

	private SingletonDemo2() {
	}

	public static synchronized SingletonDemo2 getInstance() {
		if (instance == null) {
			instance = new SingletonDemo2();
		}
		return instance;
	}
}

class SingletonDemo3 {
	//*****主要使用这个效率高 线程安全******
	// 3.饿汉式 线程安全(这种方式比较常用，但容易产生垃圾对象)
	// 优点：没有加锁，执行效率会提高 缺点：类加载时就初始化，浪费内存。
	// 它基于 classloder
	// 机制避免了多线程的同步问题，不过，instance在类装载时就实例化，虽然导致类装载的原因有很多种，在单例模式中大多数都是调用
	// getInstance 方法，
	// 但是也不能确定有其他的方式（或者其他的静态方法）导致类装载，这时候初始化 instance 显然没有达到 lazy loading 的效果
	private static SingletonDemo3 instance = new SingletonDemo3();

	private SingletonDemo3() {
	}

	public static SingletonDemo3 getInstance() {
		return instance;
	}
}

class SingletonDemo4 {
	// 4.懒汉式 双重校验锁
	// 安全且在多线程情况下能保持高性能。
	private volatile static SingletonDemo4 singleton;

	private SingletonDemo4() {
	}

	public static SingletonDemo4 getSingleton() {
		if (singleton == null) {
			synchronized (SingletonDemo4.class) {
				if (singleton == null) {
					singleton = new SingletonDemo4();
				}
			}
		}
		return singleton;
	}
}

class SingletonDemo5 {
	// 登记式/静态内部类
	//这种方式能达到双检锁方式一样的功效，但实现更简单。对静态域使用延迟初始化，应使用这种方式而不是双检锁方式。这种方式只适用于静态域的情况，双检锁方式可在实例域需要延迟初始化时使用。
	//这种方式同样利用了 classloder 机制来保证初始化 instance 时只有一个线程，它跟第 3 种方式不同的是：第 3 种方式只要 Singleton 类被装载了，那么 instance 就会被实例化（没有达到 lazy loading 效果），
	//而这种方式是 Singleton 类被装载了，instance 不一定被初始化。因为 SingletonHolder 类没有被主动使用，只有通过显式调用 getInstance 方法时，才会显式装载 SingletonHolder 类，
	//从而实例化 instance。想象一下，如果实例化 instance 很消耗资源，所以想让它延迟加载，另外一方面，又不希望在 Singleton 类加载时就实例化，因为不能确保 Singleton 类还可能在其他的地方被主动使用从而被加载，
	//那么这个时候实例化 instance 显然是不合适的。这个时候，这种方式相比第 3 种方式就显得很合理
	private static class SingletonHolder {
		private static final SingletonDemo5 INSTANCE = new SingletonDemo5();
	}

	private SingletonDemo5() {
	}

//	public static final SingletonDemo5 getInstance() {
//		return SingletonHolder.INSTANCE;
//	}
}

//enum SingletonDemo6 {
//	//枚举方式
//	//这种实现方式还没有被广泛采用，但这是实现单例模式的最佳方法。它更简洁，自动支持序列化机制，绝对防止多次实例化。
//	//这种方式是 Effective Java 作者 Josh Bloch 提倡的方式，它不仅能避免多线程同步问题，而且还自动支持序列化机制，防止反序列化重新创建新的对象，绝对防止多次实例化
//    INSTANCE;
//    public void whateverMethod() {
//    }
//}