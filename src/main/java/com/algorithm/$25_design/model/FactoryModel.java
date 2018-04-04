package com.algorithm.$25_design.model;


import com.algorithm.$8_annotation.single.ann.Location;

@Location("工程模式,简单工厂模式,抽象工厂模式")
public class FactoryModel {


//    返回主页
//            特立独行Allen
//    应用之美在于药到病除，不管黑猫白猫，能解决问题就是好猫。
//    博客园首页新随笔管理订阅订阅
//    随笔- 70  文章- 2  评论- 111
//            【Java基础】浅谈常见设计模式
//    Num1：单例模式
//    基本概念：保证一个类仅有一个实例，并提供一个访问它的全局访问点。
//
//    常见写法：
//
//    懒汉式
//
//    public class Singleton {
//        /* 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载 */
//        private static Singleton instance = null;
//
//        /* 私有构造方法，防止被实例化 */
//        private Singleton() {}
//
//        /* 1:懒汉式，静态工程方法，创建实例 */
//        public static Singleton getInstance() {
//            if (instance == null) {
//                instance = new Singleton();
//            }
//            return instance;
//        }
//    }
//    调用：
//
//            Singleton.getInstance().method();
//    优点：延迟加载（需要的时候才去加载）,适合单线程操作
//    缺点： 线程不安全，在多线程中很容易出现不同步的情况，如在数据库对象进行的频繁读写操作时。
//
//    双重线程检查模式
//
//    public class SingletonInner {
//        private static volatile SingletonInner sInst = null;  // <<< 这里添加了 volatile
//
//        /**
//         * 私有的构造函数
//         */
//        private SingletonInner() {}
//
//        public static SingletonInner getInstance() {
//            SingletonInner inst = sInst;  // <<< 在这里创建临时变量
//            if (inst == null) {
//                synchronized (SingletonInner.class) {
//                    inst = sInst;
//                    if (inst == null) {
//                        inst = new SingletonInner();
//                        sInst = inst;
//                    }
//                }
//            }
//            return inst;  // <<< 注意这里返回的是临时变量
//        }
//
//        protected void method() {
//            System.out.println("SingletonInner");
//        }
//    }
//    调用：
//
//            Singleton.getInstance().method();
//    优点：延迟加载，线程安全
//    缺点： 写法复杂，不简洁
//
//            内部类的实现
//
//    public class SingletonInner {
//        /**
//         * 内部类实现单例模式
//         * 延迟加载，减少内存开销
//         */
//        private static class SingletonHolder {
//            private static SingletonInner instance = new SingletonInner();
//        }
//
//        /**
//         * 私有的构造函数
//         */
//        private SingletonInner() {}
//
//        public static SingletonInner getInstance() {
//            return SingletonHolder.instance;
//        }
//
//        protected void method() {
//            System.out.println("SingletonInner");
//        }
//    }
//    调用：
//
//            Singleton.getInstance().method();
//    优点：延迟加载，线程安全（java中class加载时互斥的），也减少了内存消耗，推荐使用内部类方式。
//
//    Num2：工厂模式
//    基本概念：为创建对象提供过渡接口，以便将创建对象的具体过程屏蔽隔离起来，达到提高灵活性的目的。
//
//    分为三类：
//
//    简单工厂模式Simple Factory：不利于产生系列产品；
//
//    工厂方法模式Factory Method：又称为多形性工厂；
//
//    抽象工厂模式Abstract Factory：又称为工具箱，产生产品族，但不利于产生新的产品；
//
//    这三种模式从上到下逐步抽象，并且更具一般性。GOF在《设计模式》一书中将工厂模式分为两类：工厂方法模式（Factory Method）与抽象工厂模式（Abstract Factory）。将简单工厂模式（Simple Factory）看为工厂方法模式的一种特例，两者归为一类。
//
//    简单工厂模式
//
//    简单工厂模式又称静态工厂方法模式。重命名上就可以看出这个模式一定很简单。它存在的目的很简单：定义一个用于创建对象的接口。
//
//    在简单工厂模式中,一个工厂类处于对产品类实例化调用的中心位置上,它决定那一个产品类应当被实例化, 如同一个交通警察站在来往的车辆流中,决定放行那一个方向的车辆向那一个方向流动一样。
//
//    先来看看它的组成：
//
//    工厂类角色：这是本模式的核心，含有一定的商业逻辑和判断逻辑。在java中它往往由一个具体类实现。
//    抽象产品角色：它一般是具体产品继承的父类或者实现的接口。在java中由接口或者抽象类来实现。
//    具体产品角色：工厂类所创建的对象就是此角色的实例。在java中由一个具体类实现。
//    示例代码：
//
//    public class Factory{ //getClass 产生Sample 一般可使用动态类装载装入类。
//        public static Sample creator(int which){
//            if (which==1)
//                return new SampleA();
//            else if (which==2)
//                return new SampleB();
//        }
//    }
//    还有一种目前比较流行的规范是把静态工厂方法命名为valueOf或者getInstance。
//
//    valueOf：该方法返回的实例与它的参数具有同样的值，例如：
//
//    Integer a=Integer.valueOf(100); //返回取值为100的Integer对象
//    public class Complex {
//        private final float re;
//        private final float im;
//
//        private Complex(float re, float im){
//            this.re = re;
//            this.im = im;
//        }
//
//        public static Complex valueOf(float re, float im){
//            return new Complex(re, im);
//        }
//
//        public static Complex valueOfPolar(float r, float theta){
//            return new Complex((float)(r * Math.cos(theta)), (float)(r * Math.sin(theta)));
//        }
//    }
//    从上面代码可以看出，valueOf()方法能执行类型转换操作，在本例中，把int类型的基本数据转换为Integer对象。
//
//    getInstance：返回的实例与参数匹配，例如：
//
//    Calendar cal=Calendar.getInstance(Locale.CHINA); //返回符合中国标准的日历
//    工厂方法模式
//
//    工厂方法模式是简单工厂模式的进一步抽象化和推广，工厂方法模式里不再只由一个工厂类决定那一个产品类应当被实例化,这个决定被交给抽象工厂的子类去做。
//    来看下它的组成：
//
//    抽象工厂角色： 这是工厂方法模式的核心，它与应用程序无关。是具体工厂角色必须实现的接口或者必须继承的父类。在java中它由抽象类或者接口来实现。
//    具体工厂角色：它含有和具体业务逻辑有关的代码。由应用程序调用以创建对应的具体产品的对象
//    抽象产品角色：它是具体产品继承的父类或者是实现的接口。在java中一般有抽象类或者接口来实现。
//    具体产品角色：具体工厂角色所创建的对象就是此角色的实例。在java中由具体的类来实现。
//    工厂方法模式使用继承自抽象工厂角色的多个子类来代替简单工厂模式中的“上帝类”。正如上面所说，这样便分担了对象承受的压力；而且这样使得结构变得灵活 起来——当有新的产品（即暴发户的汽车）产生时，只要按照抽象产品角色、抽象工厂角色提供的合同来生成，那么就可以被客户使用，而不必去修改任何已有的代 码。可以看出工厂角色的结构也是符合开闭原则的！
//
//    示例代码：
//
//    //抽象产品角色
//    public interface Moveable {
//        void run();
//    }
//    //具体产品角色
//    public class Plane implements Moveable {
//        @Override
//        public void run() {
//            System.out.println("plane....");
//        }
//    }
//    //具体产品角色
//    public class Broom implements Moveable {
//        @Override
//        public void run() {
//            System.out.println("broom.....");
//        }
//    }
//
//    //抽象工厂
//    public abstract class VehicleFactory {
//        abstract Moveable create();
//    }
//    //具体工厂
//    public class PlaneFactory extends VehicleFactory{
//        public Moveable create() {
//            return new Plane();
//        }
//    }
//    //具体工厂
//    public class BroomFactory extends VehicleFactory{
//        public Moveable create() {
//            return new Broom();
//        }
//    }
//    //测试类
//    public class Test {
//        public static void main(String[] args) {
//            VehicleFactory factory = new BroomFactory();
//            Moveable m = factory.create();
//            m.run();
//        }
//    }
//    可以看出工厂方法的加入，使得对象的数量成倍增长。当产品种类非常多时，会出现大量的与之对应的工厂对象，这不是我们所希望的。因为如果不能避免这种情 况，可以考虑使用简单工厂模式与工厂方法模式相结合的方式来减少工厂类：即对于产品树上类似的种类（一般是树的叶子中互为兄弟的）使用简单工厂模式来实 现。
//
//    简单工厂和工厂方法模式的比较
//
//    工厂方法模式和简单工厂模式在定义上的不同是很明显的。工厂方法模式的核心是一个抽象工厂类,而不像简单工厂模式, 把核心放在一个实类上。工厂方法模式可以允许很多实的工厂类从抽象工厂类继承下来, 从而可以在实际上成为多个简单工厂模式的综合,从而推广了简单工厂模式。
//    反过来讲,简单工厂模式是由工厂方法模式退化而来。设想如果我们非常确定一个系统只需要一个实的工厂类, 那么就不妨把抽象工厂类合并到实的工厂类中去。而这样一来,我们就退化到简单工厂模式了。
//
//    抽象工厂模式
//
//    示例代码：
//
//    //抽象工厂类
//    public abstract class AbstractFactory {
//        public abstract Vehicle createVehicle();
//        public abstract Weapon createWeapon();
//        public abstract Food createFood();
//    }
//    //具体工厂类，其中Food,Vehicle，Weapon是抽象类，
//    public class DefaultFactory extends AbstractFactory{
//        @Override
//        public Food createFood() {
//            return new Apple();
//        }
//        @Override
//        public Vehicle createVehicle() {
//            return new Car();
//        }
//        @Override
//        public Weapon createWeapon() {
//            return new AK47();
//        }
//    }
//    //测试类
//    public class Test {
//        public static void main(String[] args) {
//            AbstractFactory f = new DefaultFactory();
//            Vehicle v = f.createVehicle();
//            v.run();
//            Weapon w = f.createWeapon();
//            w.shoot();
//            Food a = f.createFood();
//            a.printName();
//        }
//    }
//    在抽象工厂模式中，抽象产品 (AbstractProduct) 可能是一个或多个，从而构成一个或多个产品族(Product Family)。 在只有一个产品族的情况下，抽象工厂模式实际上退化到工厂方法模式。
//
//    总结
//
//    简单工厂模式是由一个具体的类去创建其他类的实例，父类是相同的，父类是具体的。
//    工厂方法模式是有一个抽象的父类定义公共接口，子类负责生成具体的对象，这样做的目的是将类的实例化操作延迟到子类中完成。
//    抽象工厂模式提供一个创建一系列相关或相互依赖对象的接口，而无须指定他们具体的类。它针对的是有多个产品的等级结构。而工厂方法模式针对的是一个产品的等级结构。
//    Num3：建造(Builder)模式
//    基本概念：是一种对象构建的设计模式，它可以将复杂对象的建造过程抽象出来（抽象类别），使这个抽象过程的不同实现方法可以构造出不同表现（属性）的对象。
//
//    Builder模式是一步一步创建一个复杂的对象，它允许用户可以只通过指定复杂对象的类型和内容就可以构建它们。用户不知道内部的具体构建细节。Builder模式是非常类似抽象工厂模式，细微的区别大概只有在反复使用中才能体会到。
//
//    UML结构图：
//
//    Builder模式
//
//    上图是Strategy 模式的结构图,让我们可以进行更方便的描述:
//
//    Builder：为创建一个Product对象的各个部件指定抽象接口。
//
//    ConcreteBuilder：实现Builder的接口以构造和装配该产品的各个部件，定义并明确它所创建的表示，提供一个检索产品的接口
//
//    Director：构造一个使用Builder接口的对象。
//
//    Product：表示被构造的复杂对象。ConcreateBuilder创建该产品的内部表示并定义它的装配过程。
//
//            ​
//
//    为何使用
//
//    是为了将构建复杂对象的过程和它的部件解耦。注意：是解耦过程和部件。
//    因为一个复杂的对象，不但有很多大量组成部分，如汽车，有很多部件：车轮、方向盘、发动机，还有各种小零件等等，部件很多，但远不止这些，如何将这些部件装配成一辆汽车，这个装配过程也很复杂(需要很好的组装技术)，Builder模式就是为了将部件和组装过程分开。
//
//    如何使用
//
//    首先假设一个复杂对象是由多个部件组成的，Builder模式是把复杂对象的创建和部件的创建分别开来，分别用Builder类和Director类来表示。
//
//    首先,需要一个接口，它定义如何创建复杂对象的各个部件：
//
//    public interface Builder {
//　  //创建部件A　　比如创建汽车车轮void buildPartA();
//        　  //创建部件B 比如创建汽车方向盘void buildPartB();
//        　  //创建部件C 比如创建汽车发动机void buildPartC();
//        　  //返回最后组装成品结果 (返回最后装配好的汽车)
//        　  //成品的组装过程不在这里进行,而是转移到下面的Director类中进行.
//        　  //从而实现了解耦过程和部件
//        Product getResult();
//    }
//    用Director构建最后的复杂对象，而在上面Builder接口中封装的是如何创建一个个部件(复杂对象是由这些部件组成的)，也就是说Director的内容是如何将部件最后组装成成品：
//
//    public class Director {
//        private Builder builder;
//        public Director( Builder builder ) {
//            this.builder = builder;
//　  }
//　  // 将部件partA partB partC最后组成复杂对象
//        　  //这里是将车轮 方向盘和发动机组装成汽车的过程
//        public void construct() {
//            builder.buildPartA();
//            builder.buildPartB();
//            builder.buildPartC();
//        }
//    }
//    Builder的具体实现ConcreteBuilder：
//
//    通过具体完成接口Builder来构建或装配产品的部件；
//    定义并明确它所要创建的是什么具体东西；
//    提供一个可以重新获取产品的接口。
//    public class ConcreteBuilder implements Builder {
//　Part partA, partB, partC;
//　public void buildPartA() {
//　　//这里是具体如何构建
//　}
//　public void buildPartB() {
//　　//这里是具体如何构建
//　}
//　public void buildPartC() {
//　　//这里是具体如何构建
//　}
//　public Product getResult() {
//　　//返回最后组装成品结果
//　}
//    }
//    复杂对象：产品Product：
//
//    public interface Product { }
//    复杂对象的部件：
//
//    public interface Part { }
//    我们看看如何调用Builder模式:
//
//    ConcreteBuilder builder = new ConcreteBuilder();
//    Director director = new Director( builder );
//director.construct();
//    Product product = builder.getResult();
//    Builder模式的应用
//
//    在Java实际使用中，我们经常用到"池"(Pool)的概念，当资源提供者无法提供足够的资源，并且这些资源需要被很多用户反复共享时，就需要使用池。"池"实际是一段内存，当池中有一些复杂的资源的"断肢"(比如数据库的连接池，也许有时一个连接会中断)，如果循环再利用这些"断肢"，将提高内存使用效率，提高池的性能。修改Builder模式中Director类使之能诊断"断肢"断在哪个部件上，再修复这个部件。
//
//    Num4：观察者模式
//    基本概念：观察者模式定义了一种一对多的依赖关系，让多个观察者对象同时监听某一主题对象。这个主题对象在状态发生变化时，会通知所有观察者对象，使它们能够自动更新自己。观察者模式又叫发布-订阅(Publish/Subscribe)模式。
//
//    UML结构图
//
//            观察者模式
//
//    上图是Observer 模式的结构图,让我们可以进行更方便的描述:
//
//    Subject类：它把所有对观察者对象的引用保存在一个聚集里，每个主题都可以有任何数量的观察着。抽象主题提供一个接口，可以增加和删除观察着对象。
//
//    Observer类：抽象观察者，为所有的具体观察者定义一个接口，在得到主题的通知时更新自己。
//
//    ConcreteSubject类：具体主题，将有关状态存入具体观察者对象；在具体主题的内部状态改变时，给所有登记过的观察者发出通知。
//
//    ConcreteObserver类：具体观察者，实现抽象观察者角色所要求的更新接口，以便使本身的状态与主题的状态相协调。
//
//    如何使用
//
//    例如：老师有电话号码，学生需要知道老师的电话号码以便于在合适的时候拨打，在这样的组合中，老师就是一个被观察者（Subject），学生就是需要知道信息的观察者，当老师的电话号码发生改变时，学生得到通知，并更新相应的电话记录。
//
//    先创建一个Subject类：
//
//    /**
//     * Subject(目标，Subject)：
//     * 目标知道它的观察者。可以有任意多个观察者观察同一个目标。
//     * 提供注册和删除观察者对象的接口。
//     */
//    public interface Subject {
//        public void attach(Observer mObserver);
//        public void detach(Observer mObserver);
//        public void notice();
//    }
//    创建Observer类：
//
//    /**
//     * Observer(观察者，Observer)：
//     * 为那些在目标发生改变时需要获得通知的对象定义一个更新接口。
//     */
//    public interface Observer {
//        public void update();
//    }
//    创建ConcreteSubject类：
//
//    /**
//     * ConcreteSubject(具体目标，Teacher)
//     * 将有关状态存入各ConcreteObserve对象。
//     * 当他的状态发生改变时，向他的各个观察者发出通知。
//     */
//    public class Teacher implements Subject{
//
//        private String phone;
//        private Vector students;
//
//        public Teacher(){
//            phone = "";
//            students = new Vector();
//        }
//
//        @Override
//        public void attach(Observer mObserver) {
//            students.add(mObserver);
//        }
//
//        @Override
//        public void detach(Observer mObserver) {
//            students.remove(mObserver);
//        }
//
//        @Override
//        public void notice() {
//            for(int i=0;i<students.size();i++){
//                ((Observer)students.get(i)).update();
//            }
//        }
//
//        public String getPhone() {
//            return phone;
//        }
//
//        public void setPhone(String phone) {
//            this.phone = phone;
//            notice();
//        }
//    }
//    创建ConcreteObserver类：
//
//    /**
//     * ConcreteObserver(具体观察者, Student)：
//     * 维护一个指向ConcreteSubject对象的引用。
//     * 存储有关状态，这些状态应与目标的状态保持一致。
//     * 实现Observer的更新接口以使自身状态与目标的状态保持一致。
//     */
//    public class Student implements Observer{
//
//        private String name;
//        private String phone;
//        private Teacher mTeacher;
//
//        public Student(String name,Teacher t){
//            this.name = name;
//            mTeacher = t;
//        }
//
//        public void show(){
//            System.out.println("Name:"+name+"\nTeacher'sphone:" + phone);
//        }
//
//        @Override
//        public void update() {
//            phone = mTeacher.getPhone();
//        }
//    }
//    客户端测试：
//
//    /**
//     * 观察者(Observer)模式测试类
//     */
//    public class ObserverClient {
//        public static void main(String[] args) {
//            Vector students = new Vector();
//            Teacher t = new Teacher();
//            for(int i= 0;i<10;i++){
//                Student st = new Student("Andy.Chen"+i,t);
//                students.add(st);
//                t.attach(st);
//            }
//
//            System.out.println("Welcome to Andy.Chen Blog!" +"\n"
//                    +"Observer Patterns." +"\n"
//                    +"-------------------------------");
//
//            t.setPhone("12345678");
//            for(int i=0;i<3;i++)
//                ((Student)students.get(i)).show();
//
//            t.setPhone("87654321");
//            for(int i=0;i<3;i++)
//                ((Student)students.get(i)).show();
//        }
//    }
//    程序运行结果如下：
//
//    Welcome to Andy.Chen Blog!
//    Observer Patterns.
//            -------------------------------
//    Name:Andy.Chen0
//    Teacher'sphone:12345678
//    Name:Andy.Chen1
//    Teacher'sphone:12345678
//    Name:Andy.Chen2
//    Teacher'sphone:12345678
//    Name:Andy.Chen0
//    Teacher'sphone:87654321
//    Name:Andy.Chen1
//    Teacher'sphone:87654321
//    Name:Andy.Chen2
//    Teacher'sphone:87654321
//    总结
//
//    观察者模式何时适用？
//
//    当一个抽象模型有两个方面，其中一个方面依赖于另一方面。将这二者封装在独立的对象中可以使他们各自独立地改变和复用。
//
//    当对一个对象的改变需要同时改变其它对象，而不知道具体由多少对象有待改变。
//
//    当一个对象必须通知其他对象，而它又不能假定其他对象是谁，换言之，你不希望这些对象是紧密耦合的。让耦合的双方都依赖于抽象，而不是依赖于具体。
//
//    Num5：适配器(Adapter)模式
//    基本概念：适配器模式把一个类的接口变换成客户端所期待的另一种接口，从而使原本因接口不匹配而无法在一起工作的两个类能够在一起工作。
//
//    适配器模式的用途
//
//    用电器做例子，笔记本电脑的插头一般都是三相的，即除了阳极、阴极外，还有一个地极。而有些地方的电源插座却只有两极，没有地极。电源插座与笔记本电脑的电源插头不匹配使得笔记本电脑无法使用。这时候一个三相到两相的转换器（适配器）就能解决此问题，而这正像是本模式所做的事情。
//
//    适配器模式的结构
//    适配器模式有类的适配器模式和对象的适配器模式两种不同的形式。
//
//    类适配器模式：
//
//    类适配器模式
//
//    在上图中可以看出，Adaptee类并没有sampleOperation2()方法，而客户端则期待这个方法。为使客户端能够使用Adaptee类，提供一个中间环节，即类Adapter，把Adaptee的API与Target类的API衔接起来。Adapter与Adaptee是继承关系，这决定了这个适配器模式是类的：
//
//    目标(Target)角色：这就是所期待得到的接口。注意：由于这里讨论的是类适配器模式，因此目标不可以是类。
//    源(Adapee)角色：现在需要适配的接口。
//    适配器(Adaper)角色：适配器类是本模式的核心。适配器把源接口转换成目标接口。显然，这一角色不可以是接口，而必须是具体类。
//    示例代码：
//
//    public interface Target {
//        /**
//         * 这是源类Adaptee也有的方法
//         */
//        public void sampleOperation1();
//        /**
//         * 这是源类Adapteee没有的方法
//         */
//        public void sampleOperation2();
//    }
//    上面给出的是目标角色的源代码，这个角色是以一个Java接口的形式实现的。可以看出，这个接口声明了两个方法：sampleOperation1()和sampleOperation2()。而源角色Adaptee是一个具体类，它有一个sampleOperation1()方法，但是没有sampleOperation2()方法。
//
//    public class Adaptee {
//        public void sampleOperation1(){}
//    }
//    适配器角色Adapter扩展了Adaptee,同时又实现了目标(Target)接口。由于Adaptee没有提供sampleOperation2()方法，而目标接口又要求这个方法，因此适配器角色Adapter实现了这个方法。
//
//    public class Adapter extends Adaptee implements Target {
//        /**
//         * 由于源类Adaptee没有方法sampleOperation2()
//         * 因此适配器补充上这个方法
//         */
//        @Override
//        public void sampleOperation2() {
//            //写相关的代码
//        }
//    }
//    对象适配器模式：
//
//    对象适配器
//
//    从上图可以看出，Adaptee类并没有sampleOperation2()方法，而客户端则期待这个方法。为使客户端能够使用Adaptee类，需要提供一个包装(Wrapper)类Adapter。这个包装类包装了一个Adaptee的实例，从而此包装类能够把Adaptee的API与Target类的API衔接起来。Adapter与Adaptee是委派关系，这决定了适配器模式是对象的。
//
//    示例代码：
//
//    public interface Target {
//        /**
//         * 这是源类Adaptee也有的方法
//         */
//        public void sampleOperation1();
//        /**
//         * 这是源类Adapteee没有的方法
//         */
//        public void sampleOperation2();
//    }
//
//    public class Adaptee {
//        public void sampleOperation1(){}
//    }
//    适配器类：
//
//    public class Adapter {
//        private Adaptee adaptee;
//        public Adapter(Adaptee adaptee){
//            this.adaptee = adaptee;
//        }
//        /**
//         * 源类Adaptee有方法sampleOperation1
//         * 因此适配器类直接委派即可
//         */
//        public void sampleOperation1(){
//            this.adaptee.sampleOperation1();
//        }
//        /**
//         * 源类Adaptee没有方法sampleOperation2
//         * 因此由适配器类需要补充此方法
//         */
//        public void sampleOperation2(){
//            //写相关的代码
//        }
//    }
//    类适配器和对象适配器的权衡
//
//    类适配器使用对象继承的方式，是静态的定义方式；而对象适配器使用对象组合的方式，是动态组合的方式。
//    对于类适配器由于适配器直接继承了Adaptee，使得适配器不能和Adaptee的子类一起工作，因为继承是静态的关系，当适配器继承了Adaptee后，就不可能再去处理  Adaptee的子类了。
//
//    对于对象适配器一个适配器可以把多种不同的源适配到同一个目标。换言之，同一个适配器可以把源类和它的子类都适配到目标接口。因为对象适配器采用的是对象组合的关系，只要对象类型正确，是不是子类都无所谓。
//
//    对于类适配器适配器可以重定义Adaptee的部分行为，相当于子类覆盖父类的部分实现方法。
//
//    对于对象适配器要重定义Adaptee的行为比较困难，这种情况下，需要定义Adaptee的子类来实现重定义，然后让适配器组合子类。虽然重定义Adaptee的行为比较困难，但是想要增加一些新的行为则方便的很，而且新增加的行为可同时适用于所有的源。
//
//    对于类适配器，仅仅引入了一个对象，并不需要额外的引用来间接得到Adaptee。
//
//    对于对象适配器，需要额外的引用来间接得到Adaptee。
//
//    建议尽量使用对象适配器的实现方式，多用合成或聚合、少用继承。当然，具体问题具体分析，根据需要来选用实现方式，最适合的才是最好的。
//
//    适配器模式的优点
//
//    更好的复用性：系统需要使用现有的类，而此类的接口不符合系统的需要。那么通过适配器模式就可以让这些功能得到更好的复用。
//
//    更好的扩展性：在实现适配器功能的时候，可以调用自己开发的功能，从而自然地扩展系统的功能。
//
//    适配器模式的缺点
//
//　　过多的使用适配器，会让系统非常零乱，不易整体进行把握。比如，明明看到调用的是A接口，其实内部被适配成了B接口的实现，一个系统如果太多出现这种情况，无异于一场灾难。因此如果不是很有必要，可以不使用适配器，而是直接对系统进行重构。
//
//    Num6：代理模式
//    基本概念：为其他对象提供一种代理以控制对这个对象的访问。也可以说，在出发点到目的地之间有一道中间层，意为代理。
//
//    为什么要使用
//
//    授权机制不同级别的用户对同一对象拥有不同的访问权利，如在论坛系统中，就使用Proxy进行授权机制控制，访问论坛有两种人：注册用户和游客(未注册用户)，论坛就通过类似ForumProxy这样的代理来控制这两种用户对论坛的访问权限。
//
//    某个客户端不能直接操作到某个对象，但又必须和那个对象有所互动。
//
//    举例两个具体情况：
//
//    如果那个对象是一个是很大的图片，需要花费很长时间才能显示出来，那么当这个图片包含在文档中时，使用编辑器或浏览器打开这个文档，打开文档必须很迅速，不能等待大图片处理完成，这时需要做个图片Proxy来代替真正的图片。
//    如果那个对象在Internet的某个远端服务器上，直接操作这个对象因为网络速度原因可能比较慢，那我们可以先用Proxy来代替那个对象。
//    总之原则是，对于开销很大的对象，只有在使用它时才创建，这个原则可以为我们节省很多宝贵的Java内存。所以，有些人认为Java耗费资源内存，我以为这和程序编制思路也有一定的关系。
//
//    如何使用
//
//    以论坛系统为例，访问论坛系统的用户有多种类型：注册普通用户、论坛管理者、系统管理者、游客。注册普通用户才能发言，论坛管理者可以管理他被授权的论坛，系统管理者可以管理所有事务等，这些权限划分和管理是使用Proxy完成的。
//    在Forum中陈列了有关论坛操作的主要行为，如论坛名称，论坛描述的获取和修改，帖子发表删除编辑等，在ForumPermissions中定义了各种级别权限的用户：
//
//    public class ForumPermissions implements Cacheable {
//        /**
//         * Permission to read object.
//         */
//        public static final int READ = 0;
//
//        /**
//         * Permission to administer the entire sytem.
//         */
//        public static final int SYSTEM_ADMIN = 1;
//
//        /**
//         * Permission to administer a particular forum.
//         */
//        public static final int FORUM_ADMIN = 2;
//
//        /**
//         * Permission to administer a particular user.
//         */
//        public static final int USER_ADMIN = 3;
//
//        /**
//         * Permission to administer a particular group.
//         */
//        public static final int GROUP_ADMIN = 4;
//
//        /**
//         * Permission to moderate threads.
//         */
//        public static final int MODERATE_THREADS = 5;
//
//        /**
//         * Permission to create a new thread.
//         */
//        public static final int CREATE_THREAD = 6;
//
//        /**
//         * Permission to create a new message.
//         */
//        public static final int CREATE_MESSAGE = 7;
//
//        /**
//         * Permission to moderate messages.
//         */
//        public static final int MODERATE_MESSAGES = 8;
//
//        public boolean isSystemOrForumAdmin() {
//            return (values[FORUM_ADMIN] || values[SYSTEM_ADMIN]);
//        }
//
////相关操作代码
//    }
//    因此，Forum中各种操作权限是和ForumPermissions定义的用户级别有关系的，作为接口Forum的实现：ForumProxy正是将这种对应关系联系起来。比如，修改Forum的名称，只有论坛管理者或系统管理者可以修改，代码如下：
//
//    public class ForumProxy implements Forum {
//        private ForumPermissions permissions;
//        private Forum forum;
//    this.authorization = authorization;
//
//        public ForumProxy(Forum forum, Authorization authorization,ForumPermissions permissions){
//            this.forum = forum;
//            this.authorization = authorization;
//            this.permissions = permissions;
//        }
//    .....
//        public void setName(String name) throws UnauthorizedException,
//                ForumAlreadyExistsException{
//            //只有是系统或论坛管理者才可以修改名称
//            if (permissions.isSystemOrForumAdmin()) {
//                forum.setName(name);
//            }
//　　  else {
//　　　 throw new UnauthorizedException();
//　　  }
//        }
//    ...
//
//    }
//    而DbForum才是接口Forum的真正实现，以修改论坛名称为例：
//
//    public class DbForum implements Forum, Cacheable {
//    ...
//        public void setName(String name) throws ForumAlreadyExistsException {
//　　....
//            this.name = name;
//　　     //这里真正将新名称保存到数据库中
//　　     saveToDb();
//　　....
//        }
//    ...
//    }
//    凡是涉及到对论坛名称修改这一事件，其他程序都首先得和ForumProxy打交道，由ForumProxy决定是否有权限做某一样事情，ForumProxy是个名副其实的"网关"，"安全代理系统"。
//    在平时应用中，无可避免总要涉及到系统的授权或安全体系，不管你有无意识的使用Proxy，实际你已经在使用Proxy了。
//
//    流程图
//
//            代理模式
//
//    Num7：装饰模式
//    基本概念：装饰模式(Decorator)，动态地给一个对象添加一些额外的职责，就增加功能来说，装饰模式比生成子类更为灵活。
//
//    UML结构图
//
//            装饰模式
//
//    上图是Decorator 模式的结构图,让我们可以进行更方便的描述:
//
//    Component是定义一个对象接口，可以给这些对象动态地添加职责。
//
//    ConcreteComponent是定义了一个具体的对象，也可以给这个对象添加一些职责。
//
//    Decorator是装饰抽象类，继承了Component,从外类来扩展Component类的功能，但对于Component来说，是无需知道Decorator存在的。ConcreteDecorator就是具体的装饰对象，起到给Component添加职责的功能。
//
//    如何使用
//
//    假设情景：某人装扮自己形象，穿衣服，裤子，鞋子，戴帽子等来把自己给包装起来，需要把所需的功能按正确的顺序串联起来进行控制，我们应该如何设计才能做到呢？如下，先看下代码结构图：
//
//    先创建一个接口类：Component.java
//
//    public interface Component {
//        void show();
//    }
//    创建一个具体的 ConcreteComponent 来实现 Component 接口：Person.java
//
//    public class Person implements Component{
//        private String name;
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public Person(String name){
//            this.name = name;
//        }
//
//        @Override
//        public void show() {
//            System.out.println("装扮的" + name);
//        }
//    }
//    创建装饰类 Decorator 实现 Component 接口
//
//    public class Decorator implements Component{
//        private Component mComponent;
//        public void decoratorObj(Component component){
//            mComponent = component;
//        }
//
//        @Override
//        public void show() {
//            if(mComponent != null){
//                mComponent.show();
//            }
//        }
//    }
//    分别创建具体的装饰类：Jeans.java , Pelisse.java, Sandal.java ...等等，分别继承 Decorator.java 类
//
//    /** 牛仔裤 */
//    public class Jeans extends Decorator {
//        @Override
//        public void show(){
//            System.out.println("穿牛仔裤");
//            super.show();
//        }
//
//    }
//    客户端测试类
//
//    /**
//     * 装饰模式测试客户端
//     */
//    public class DecoratorClient {
//        public static void main(String[] args) {
//            System.out.println("Welcome to Andy.Chen Blog!" +"\n"
//                    +"Decorator Patterns." +"\n");
//
//            Person mPerson = new Person("Andy");
//
//            Sandal mSandal = new Sandal();
//            Jeans mJeans = new Jeans();
//            TShirt mShirt = new TShirt();
//
//            mShirt.decoratorObj(mPerson);
//            mJeans.decoratorObj(mShirt);
//            mSandal.decoratorObj(mJeans);
//            mSandal.show();
//        }
//    }
//    测试结果
//
//    Welcome to Andy.Chen Blog!
//    Decorator Patterns.
//
//    穿凉鞋
//            穿牛仔裤
//    穿T-Shirt
//            装扮的Andy
//    总结
//
//    Decorator模式有以下的优缺点：
//
//    比静态继承更灵活与对象的静态继承相比，Decorator模式提供了更加灵活的向对象添加职责的方式，可以使用添加和分离的方法，用装饰在运行时刻增加和删除职责。使用继承机制增加职责需要创建一个新的子类，如果需要为原来所有的子类都添加功能的话，每个子类都需要重写，增加系统的复杂度，此外可以为一个特定的Component类提供多个Decorator，这种混合匹配是适用继承很难做到的。
//
//    避免在层次结构高层的类有太多的特征，Decorator模式提供了一种“即用即付”的方法来添加职责，他并不试图在一个复杂的可订制的类中支持所有可预见的特征，相反可以定义一个简单的类，并且用Decorator类给他逐渐的添加功能，从简单的部件组合出复杂的功能。
//
//    Decorator 与它的Component不一样Decorator是一个透明的包装，如果我们从对象标识的观点出发，一个被装饰了的组件与这个组件是有差别的，因此使用装饰时不应该以来对象标识。
//
//    产生许多小对象，采用Decorator模式进行系统设计往往会产生许多看上去类似的小对象，这些对象仅仅在他们相互连接的方式上有所不同。
//
//    参考地址：
//
//            1，http://www.cnblogs.com/forlina/archive/2011/06/21/2086114.html
//
//            2，http://www.cnblogs.com/java-my-life/archive/2012/04/13/2442795.html
//
//            3，http://blog.csdn.net/cjjky/article/details/7478788
//
//            4，http://blog.csdn.net/cjjky/article/details/7384951
//
//            5，http://blog.csdn.net/cjjky/article/details/7327200
//
//    分类: 设计模式
//    标签: Java
//    好文要顶 关注我 收藏该文
//            cryAllen
//    关注 - 23
//    粉丝 - 115
//            +加关注
//4 0
//        « 上一篇：【Java基础】并发
//» 下一篇：【Java基础】反射和注解
//    posted @ 2016-06-29 17:28 cryAllen 阅读(21145) 评论(0) 编辑 收藏
//    刷新评论刷新页面返回顶部
//    注册用户登录后才能发表评论，请 登录 或 注册，访问网站首页。
//            【推荐】超50万VC++源码: 大型组态工控、电力仿真CAD与GIS源码库！
//            【招聘】花大价钱找技术大牛我们是认真的！
//            【腾讯云】买域名送解析+SSL证书+建站
//【活动】2050 科技公益大会 - 年青人因科技而团聚
//            qcloud_C1_0402
//    最新IT新闻:
//            · 特斯拉市值超过福特一年后首次被反超
//· Waymo将和本田开发全新无人车型：人货都能拉
//· FB将允许用户针对帖子被误删提出申诉 小扎将之比作民主体制
//· 分析师给Snap卖出评级，用户对其观感由“有趣”变成“烦人”
//            · SpaceX明日发射“龙”飞船 为国际空间站运送补给
//» 更多新闻...
//    阿里云0308
//    最新知识库文章:
//            · 写给自学者的入门指南
//· 和程序员谈恋爱
//· 学会学习
//· 优秀技术人的管理陷阱
//· 作为一个程序员，数学对你到底有多重要
//» 更多知识库文章...
//
//    昵称：cryAllen
//    园龄：6年6个月
//    粉丝：115
//    关注：23
//            +加关注
//            <	2018年4月	>
//    日	一	二	三	四	五	六
//25	26	27	28	29	30	31
//        1	2	3	4	5	6	7
//        8	9	10	11	12	13	14
//        15	16	17	18	19	20	21
//        22	23	24	25	26	27	28
//        29	30	1	2	3	4	5
//    搜索
//
//            常用链接
//    我的随笔
//            我的评论
//    我的参与
//            最新评论
//    我的标签
//            我的标签
//    Android(36)
//    Java(10)
//    json(1)
//    多层架构(1)
//    架构思想(1)
//    三层架构(1)
//    随笔分类
//    Android(39)
//Asp.Net (3)
//    CLR Via C# 学习笔记(3)
//    Java(8)
//    基础学习(2)
//    框架杂想(1)
//    前端技术(3)
//    设计模式(3)
//    数据库(2)
//    杂记(6)
//    随笔档案
//2018年2月 (1)
//2018年1月 (1)
//2017年12月 (1)
//2017年10月 (1)
//2017年8月 (1)
//2017年7月 (1)
//2017年6月 (2)
//2017年5月 (1)
//2017年4月 (1)
//2017年3月 (3)
//2017年2月 (7)
//2017年1月 (4)
//2016年12月 (1)
//2016年11月 (1)
//2016年10月 (1)
//2016年9月 (1)
//2016年8月 (2)
//2016年7月 (2)
//2016年6月 (9)
//2016年5月 (2)
//2016年2月 (2)
//2015年4月 (4)
//2015年2月 (1)
//2015年1月 (2)
//2013年12月 (1)
//2013年2月 (1)
//2012年11月 (1)
//2012年10月 (3)
//2012年9月 (2)
//2012年6月 (3)
//2012年1月 (1)
//2011年10月 (1)
//2011年9月 (5)
//    相关资源
//            Android开发技术周报
//    cnBeta
//            JakeWharton
//    Performance
//            技术小黑屋
//    最新评论
//1. Re:Android组件化框架设计与实践
//    学习 不错
//--ekraha
//2. Re:Android组件化框架设计与实践
//            Bella
//--RAE
//3. Re:Android组件化框架设计与实践
//    @cryAllenAPP 的任何架构设计不考虑 UI 是不现实的， 考虑 UI的话最佳实践就必然要用异步，用异步的话 MVP 模式就不合适了。总之，你所要解决的问题，MVVM 模式其实已经解决了。........
//            --海淀游民
//4. Re:Android组件化框架设计与实践
//    @海淀游民 每个组件里是可以用MVP，我这边更侧重是组件跟组件之间隔离和通信，RXjava是另外一个方面吧。...
//            --cryAllen
//5. Re:Android组件化框架设计与实践
//    除了路由器外，楼主的设计貌似就是个 MVP 模式的加强，所以 MVP 没解决的问题一样没有解决，就是异步处理混乱不堪。APP 不是 J2ee 程序，异步处理遍布全身，直接套 J2ee 那套并不好。我的......
//            --海淀游民
//            阅读排行榜
//1. ProGuard代码混淆技术详解(37213)
//2. 谈谈Android 6.0运行时权限理解(32143)
//3. 【Java基础】浅谈常见设计模式(21129)
//4. "浅谈Android"第一篇：Android系统简介(20214)
//5. Android 常见工具类封装(15423)
//    Copyright ©2018 cryAllen
}
