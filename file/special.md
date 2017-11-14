### 特殊功能说明
#### 目录
- [DataBinding](#DataBinding)
- [布局结构说明](#布局结构说明)
- [反射创建说明](#反射创建说明)
- [界面加载流程](#界面加载流程)

#### DataBinding

&emsp;&emsp;考虑到现在很多人项目中并没有大量使用这个东西，所以**dataBinding功能默认是关闭的**,开启dataBinding只需要在presenter或view的实现子类复写enableDataBinding方法，并返回true即可。<br>
&emsp;&emsp;基本使用，参考demo中PersonDataBindingActivity界面，首先需要自己在view的实现类中声明一个当前界面对应类型的dataBinding,然后调用getDataBinding方法为这个变量赋值，后面就可以直接使用dataBinding了。

~~~java
private ActivityPersonBinding mBinding;
    @Override
    public void initView (IBasePresenter presenter) {
        super.initView(presenter);
        mBinding = getDataBinding();
        mBinding.activityPersonCommitBtn.setOnClickListener(presenter);
    }
~~~
实现原理：<br>
&emsp;&emsp;MVPH对于dataBinding集成非常简单，就是采用配置参数判断使用传统方法加载布局还是使用dataBinding进行加载

使用建议：<br>
&emsp;&emsp;1.没用过dataBinding的同学，建议先去看下dataBinding的基本使用方法，然后再使用这个功能。<br>
&emsp;&emsp;2.使用dataBinding时，建议仅仅使用它的数据双向绑定以及直接通过id调用控件的功能。不要在xml布局中写逻辑，虽然它支持在xml布局中设置监听器，但是建议不要这么做，因为这样可能会引起后面逻辑的混乱与维护的复杂性。简单来说就是，不要过度依赖dataBinding。<br>
&emsp;&emsp;3.虽然dataBinding现在来说，相比之前已经成熟很多了，不过依然有个问题就是报错定位不准确的问题，这个问题在开发时绝对是令人抓狂的，所以，在使用dataBinding的时候，尽量一个界面一个界面的进行开发，以保证出错时方便查找。<br>
&emsp;&emsp;4.**布局的要求（重点）**，dataBinding布局除了官方的要求外，在MVPH中使用还有几点强制的要求。这里只说明布局要求，具体为什么有这些限制，请看下一章节的布局结构说明。布局示例参考demo中的activity_person.xml
* 首先，**根布局必须为线性垂直布局**
* 其次，**根布局id必须为dataBindingRootLayout**
* 最后，**根布局中必须只能包含一个直接子布局**。


~~~xml
<layout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
>
    <data>
        <variable name="person" type="com.xujl.mvpllirary.json.PersonPayload"/>
    </data>
    <LinearLayout android:id="@+id/dataBindingRootLayout"
                  android:layout_width="match_parent"
                  android:layout_height="match_parent"
                  android:background="@color/layoutBgWhite"
                  android:orientation="vertical">
        <ScrollView android:layout_width="match_parent"
                    android:layout_height="match_parent">
                    <!--省略中间布局细节 -->
        </ScrollView>
    </LinearLayout>
</layout>
~~~

#### 布局结构说明

![](https://raw.githubusercontent.com/AcgnCodeMonkey/MVPLibrary/master/file/布局结构示意图.png)

&emsp;&emsp;本库的自动添加toolBar功能和全局空布局功能都是基于为布局文件动态添加一个父布局(垂直线性布局)，然后动态结合toolBar和真实布局实现的。从示意图可以明显看出，contentLayout才是项目中的布局文件，rootView是通过代码生成的布局，toolBar则是用rootView去添加到自己内部第一个位置的，需要特别指出的是，布局配置类中有个属性是，是否自动为布局文件创建父布局，**这个属性只有在关闭使用MVPH的自动生成toolBar功能时才会生效**，至于原因，结合上面讲的结构，我想大家应该都知道为什么了。<br>
&emsp;&emsp;通过上面的说明，有的同学应该已经知道为什么在dataBinding布局中需要有那些强制要求了。这些强制要求就是为了dataBinding布局和普通布局保持结构的统一性。

#### 反射创建说明

Presenter中持有view和model的强引用，view和model的声明类型采用的是类泛型传递到基类的，实际类型则是通过子类传递，然后进行反射创建，目前支持两种方式进行反射创建。（具体逻辑参考Presenter类中的createViewModel方法）
* 子类Presenter复写对应方法，直接返回对应view和model的类类型，通过类类型反射创建
demo中只有MainActivity采用了方法1进行反射创建，其他所有界面均采用方法2实现
~~~java
  @Override
  protected Class<? extends IMainActivityView> getViewClassType () {
      return MainActivity.class;
  }

  @Override
  protected Class<? extends IMainActivityModel> getModelClassType () {
      return MainActivityModel.class;
  }
~~~
* 由项目基类统一返回view和model的包路径，然后拼接出view和model的全限定名进行反射创建
~~~java
    @Override
    protected String getViewClassPackageName () {
        return AppApplication.getInstance().getViewPackageName();
    }

    @Override
    protected String getModelClassPackageName () {
        return AppApplication.getInstance().getModelPackageName();
    }
~~~
##### 注意点：
* 基类反射逻辑为先判断子类是否返回了类类型，如果没有才会去尝试创建全限定名，也就是说方法1的优先级高于方法2
* 两种方法优缺点对比：1方法优点在于非常自由，可以随意命名view和model类，然后进行传递，缺点在于比较繁琐，每个Presenter实现子类都必须要传递（mvp模式下）。2方法的优点在于非常简单，只需要自己在项目Presenter基类中复写一次对应方法，返回view和model的包路径，然后所有的子类无需再次复写，缺点在于：首先，对view和model的命名要求必须遵循一定规范（可以和demo中的命名规范不同，因为可以通过Presenter复写全限定名拼接方法来改变规则），其次，要求view和model的分包必须放在一起（至少activity对应的view和model,fragment对应的view和model，的分包必须各自在一起），因为如果包路径太多就会造成经常需要复写返回包路径的方法，这样的话，还不如直接使用方法1，最后，因为方法2采用的是拼接全限定名的方式，所以Presenter没有直接引用view和model的类名，这样有一个风险就是造成，以为某个view和model类未使用，而被误删。

总的来说，**推荐使用方法2进行反射**，虽然方法2缺点很多，但是只要习惯了这种创建方法，使用起来就会非常方便，需要特别指出的是使用方法2进行反射时就必须保证不混淆model和view的所有类，否则会找不到类。

### 界面加载流程

这里以activity为例，fragment加载逻辑与之类似

![](https://raw.githubusercontent.com/AcgnCodeMonkey/MVPLibrary/master/file/加载流程图.png)

&emsp;&emsp;简单解释下流程图的意思，当activity调用onCreate方法时会首先调用一个firstLoading的方法，此方法位于super方法调用前，因此可以用来设置某些特殊功能，紧接着调用super方法，之后会调用createViewModel方法创建view和Model，此方法内部会进行逻辑判断是使用反射创建实例还是使用子类默认创建的实例，然后下一步开始创建布局视图，布局视图创建完毕后开始调用view类的初始化控件方法，下一步会判断当前界面是否需要权限，如果有未获取的权限则会跳入权限申请循环方法，知道授权完毕，进入下一步加载，然后调用presenrer的逻辑初始化方法，最后回调自定义生命周期回调方法。

&emsp;&emsp;备注：需要注意的一点是，在0.1.2版本之后，onCreate中方法调用只到mView.initView(),下一步的方法是在activity中的onWindowFocusChanged后调用的，这是为了防止界面还未完全显示时，进行某些ui操作时造成程序崩溃（比如弹出popupWindow）
