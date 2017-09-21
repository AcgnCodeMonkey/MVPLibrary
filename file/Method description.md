### 1.接口方法说明
#### IBaseVP
&emsp;&emsp;view和presenter共有方法，为了兼容mvp和mvc开发，此接口包含部分需要两边同时可以
控制界面加载的部分配置方法<br>

|             方法名        | 说明           |
|:---:|:---:|
| boolean enableToolBar ()   | 是否使用导航，只有presenter和view都返回true才会启用，默认是使用导航 |
| int getLayoutId ()   | 布局id，会通过presenter的isMVP()方法判断调用view还是presenter的此方法 |
| int getToolBarId ()   | 导航栏toolBarId,调用规则同上 |
| boolean enableDataBinding ()   | 是否使用dataBinding进行布局绑定，只有view或presenter其中一个返回true就会启用，默认是不使用 |
| boolean isAddParentLayout ()   | 是否为当前内容布局自动添加一个父布局，只有presenter和view都返回true才会启用，默认是添加 |<br>
备注：需要view和presenter共同控制的配置中，凡是默认开启的，只要其中一方返回false就会关闭，凡是默认关闭的，只要其中一个返回true就会开启，具体配置可以参见LayoutConfig类
#### IBaseView
&emsp;&emsp;BaseView基础接口，提供一些简单方法供外接调用<br>

|             方法名        | 说明           |
|:---:|:---:|
| void initView (IBasePresenter presenter)   | 初始化控件，子类复写此方法时必须调用super方法 |
| View createUI (IBasePresenter presenter)   | 创建视图UI，调用此方法会返回根视图，通常此方法由框架内部调用 |
| View exposeRootView ()   | 获取当前界面根布局 |
| <T extends View> T findView (int id)   | 通过id获取控件，直接调用此方法找控件可以免去强转和对mRootView的调用 |
| BaseToolBarModule getToolBarModule ()   | 获取当导航模块，用以修改导航属性 |
| BaseToolBarModule createToolBarModule (IBaseView view, IBasePresenter presenter, int layoutId)   | 创建导航模块，此方法由BaseViewHelper类调用，需要修改默认的导航模块类时可以复写此方法 |
| void showToastMsg (Context context, String msg, int code)  | 显示toast消息，code为消息分级 |
| void showToastMsg (Context context, String msg, int code, int time)   | 上面方法的重载方法，可以自定义显示时间长度 |
| void showLoading ()   | 显示界面加载，由子类实现，具体实现根据自己需求来定 |
| void dismissLoading ()   | 隐藏界面加载 |
#### IBasePresenter
&emsp;&emsp;BaseActivityPresenter和BaseFragmentPresenter的基础接口<br>

|             方法名        | 说明           |
|:---:|:---:|
| void exit() | 退出当前activity，建议退出界面时统一使用此方法，而不是finish()方法，以便进行某些退出时的统一处理 |
| Context exposeContext () | 获取context |
|  BaseActivityPresenter exposeActivity () | 获取当前activity |
|  IBaseView exposeView () | 获取当前视图BaseView |
| IBaseModel exposeModel () | 获取当前BaseModel |
| boolean isMVP () | 当前界面是否使用mvp进行开发，默认是true,关闭mvp模式时，可以不用传递presenter上的两个泛型和presenter中两个回传类类型的方法 |
#### IBaseModel
&emsp;&emsp;BaseModel基础接口<br>

|             方法名        | 说明           |
|:---:|:---:|
| <T> T fromJson (String json, Class<T> classOfT) | 解析json |
| String toJson (Object src) | 生成json |
备注：BaseModel中封装的方法非常少，所有网络请求和数据库操作都建议开发者自行封装以满足自身需求，这里只是规定一个结构类型
#### LifeCycleCallback
&emsp;&emsp;生命周期回调接口，对应几个生命周期，不过多说明

---
### 2.mvp主要类，方法说明
|  |  |
