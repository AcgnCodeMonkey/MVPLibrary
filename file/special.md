### 特殊功能说明
#### 1.DataBinding

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
&emsp;&emsp;2.使用dataBinding时，建议仅仅使用它的数据双向绑定以及直接通过id调用控件的功能不要在xml布局中写逻辑，虽然它支持在xml布局中设置监听器，但是建议不要这么做，因为这样可能会引起后面逻辑的混乱与维护的复杂性。简单来说就是，不要过度依赖dataBinding。<br>
&emsp;&emsp;3.虽然dataBinding现在来说，相比之前已经成熟很多了，不过依然有个问题就是报错定位不准确的问题，这个问题在开发时绝对是令人抓狂的，所以，在使用dataBinding的时候，尽量一个界面一个界面的进行开发，以保证出错时方便查找。<br>
&emsp;&emsp;4.**布局的要求（重点）**，dataBinding布局除了官方的要求外，在MVPH中使用还有几点强制的要求。首先，**根布局必须为线性垂直布局**，其次根布局id必须为dataBindingRootLayout，最后，根布局中必须只能包含一个直接子布局。这里只说明布局要求，具体为什么有这些限制，请看下一章节的布局结构说明。
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
#### 2.布局结构说明

![](https://raw.githubusercontent.com/AcgnCodeMonkey/MVPLibrary/master/file/布局结构示意图.png)

&emsp;&emsp;本库的自动添加toolBar功能和全局空布局功能都是基于为布局文件动态添加一个父布局(垂直线性布局)，然后动态结合toolBar和真实布局实现的。从示意图可以明显看出，contentLayout才是项目中的布局文件，rootView是通过代码生成的布局，toolBar则是用rootView去添加到自己内部第一个位置的，需要特别指出的是，布局配置类中有个属性是，是否自动为布局文件创建父布局，**这个属性只有在关闭使用MVPH的自动生成toolBar功能时才会生效**，至于原因，结合上面讲的结构，我想大家应该都知道为什么了。<br>
&emsp;&emsp;通过上面的说明，有的同学应该已经知道为什么在dataBinding布局中需要有那些强制要求了。这些强制要求就是为了dataBinding布局和普通布局保持结构的统一性。
