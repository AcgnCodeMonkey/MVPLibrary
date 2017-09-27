#### 目录
* [接口方法说明](#接口方法说明)
  + [IBaseVP](#ibasevp)
  + [IBaseView](#IBaseView)
  + [IBasePresenter](#IBasePresenter)
  + [IBaseModel](#IBaseModel)
  + [LifeCycleCallback](#LifeCycleCallback)
* [主要类方法说明](#主要类方法说明)
  + [BaseModel](#BaseModel)
  + [BaseView](#BaseView)
  + [BaseActivityPresenter](#BaseActivityPresenter)
  + [BaseFragmentPresenter](#BaseFragmentPresenter)
* [其他部分类说明](#其他部分类说明)
  + [BaseMvpHelper](#BaseMvpHelper)

### 接口方法说明
#### IBaseVP
&emsp;&emsp;view和presenter共有方法，为了兼容mvp和mvc开发，此接口包含部分需要两边同时可以
控制界面加载的部分配置方法

|             方法名        | 说明           |
|:---:|:---:|
| boolean enableToolBar ()   | 是否使用导航，只有presenter和view都返回true才会启用，默认是使用导航 |
| int getLayoutId ()   | 布局id，会通过presenter的isMVP()方法判断调用view还是presenter的此方法 |
| int getToolBarId ()   | 导航栏toolBarId,调用规则同上 |
| boolean enableDataBinding ()   | 是否使用dataBinding进行布局绑定，只有view或presenter其中一个返回true就会启用，默认是不使用 |
| boolean isAddParentLayout ()   | 是否为当前内容布局自动添加一个父布局，只有presenter和view都返回true才会启用，默认是添加 |

备注：需要view和presenter共同控制的配置中，凡是默认开启的，只要其中一方返回false就会关闭，凡是默认关闭的，只要其中一个返回true就会开启，具体配置可以参见LayoutConfig类

#### IBaseView
&emsp;&emsp;BaseView基础接口，提供一些简单方法供外接调用

|             方法名        | 说明           |
|:---:|:---:|
| void initView (IBasePresenter presenter)   | 初始化控件，子类复写此方法时必须调用super方法 |
| View createUI (IBasePresenter presenter)   | 创建视图UI，调用此方法会返回根视图，通常此方法由框架内部调用 |
| View exposeRootView ()   | 获取当前界面根布局 |
| <T extends View> T findView (int id)   | 通过id获取控件，直接调用此方法找控件可以免去强转和对mRootView的调用 |
| <D extends ViewDataBinding> D getDataBinding ()   | 获取界面dataBinding |
| BaseToolBarModule getToolBarModule ()   | 获取当导航模块，用以修改导航属性 |
| BaseToolBarModule createToolBarModule (IBaseView view, IBasePresenter presenter, int layoutId)   | 创建导航模块，此方法由BaseViewHelper类调用，需要修改默认的导航模块类时可以复写此方法 |
| void showToastMsg (Context context, String msg, int code)  | 显示toast消息，code为消息分级 |
| void showToastMsg (Context context, String msg, int code, int time)   | 上面方法的重载方法，可以自定义显示时间长度 |
| void showLoading ()   | 显示界面加载，由子类实现，具体实现根据自己需求来定 |
| void dismissLoading ()   | 隐藏界面加载 |

#### IBasePresenter
&emsp;&emsp;BaseActivityPresenter和BaseFragmentPresenter的基础接口

|             方法名        | 说明           |
|:---:|:---:|
| void exit() | 退出当前activity，建议退出界面时统一使用此方法，而不是finish()方法，以便进行某些退出时的统一处理 |
| Context exposeContext () | 获取context |
|  BaseActivityPresenter exposeActivity () | 获取当前activity |
|  IBaseView exposeView () | 获取当前视图BaseView |
| IBaseModel exposeModel () | 获取当前BaseModel |
| boolean isMVP () | 当前界面是否使用mvp进行开发，默认是true,关闭mvp模式时，可以不用传递presenter上的两个泛型和presenter中两个回传类类型的方法 |

#### IBaseModel
&emsp;&emsp;BaseModel基础接口

|             方法名        | 说明           |
|:---:|:---:|
| <T> T fromJson (String json, Class<T> classOfT) | 解析json |
| String toJson (Object src) | 生成json |

备注：BaseModel中封装的方法非常少，所有网络请求和数据库操作都建议开发者自行封装以满足自身需求，这里只是规定一个结构类型

#### LifeCycleCallback
&emsp;&emsp;生命周期回调接口，对应几个生命周期，不过多说明

---
### 主要类方法说明
&emsp;&emsp;对于实现的相应的接口的方法不再进行描述
#### BaseModel
&emsp;&emsp;Model基类

|             方法名        | 说明           |
|:---:|:---:|
| BaseModelHelper getModelHelper () | 获取当前类helper |
| void setModelHelper (BaseModelHelper modelHelper) | 修改当前默认的helper类 |

#### BaseView
&emsp;&emsp;View基类

|             方法名        | 说明           |
|:---:|:---:|
| BaseViewHelper getViewHelper () | 获取当前类helper |
|BaseViewHelper setViewHelper (IBasePresenter presenter) | 修改当前默认的helper类 |

#### BaseActivityPresenter
&emsp;&emsp;ActivityPresenter基类

|             方法名        | 说明           |
|:---:|:---:|
| abstract void initPresenter (Bundle savedInstanceState) | Activity逻辑初始化抽象方法，通常需要最终子类实现此方法 |
| abstract void autoCreateViewModel () | 关闭mvp进行开发时，此方法会被调用，用来创建model和view实例，因此需要自己的抽象基类实现此方法，参考demo中的CommonActivityPresenter类中用法 |
| void continueLoading (final Bundle savedInstanceState) | 私有化方法，此方法中会调用initPresenter方法 |
| void createViewModel () | 实例化model和view，会根据是否开启mvp，来判断使用反射创建还是调用autoCreateViewModel进行创建 |
| void setmLifeCycleCallback (LifeCycleCallback mLifeCycleCallback) | 设置生命周期回调，此回调可以获取activity中所有生命周期回调 |
| BasePresenterHelper getPresenterHelper () | 获取当前类helper |
| void setPresenterHelper (BasePresenterHelper presenterHelper) | 设置当前类helper |
| void firstLoading (Bundle savedInstanceState) | 初始加载方法空实现，此方法会位于super.onCreate前被调用，可以用来满足某些特殊功能 |
| String[] needPermissions () | 页面需要申请的权限数组 |
| void createLayout () | 创建视图 |
| Class<? extends V> getViewClassType () | 子类可以复写此方法返回view的类类型 |
| Class<? extends M> getModelClassType () | 子类可以复写此方法返回model的类类型 |
| String getViewClassPackageName () | 子类返回view类所在包名 |
| String getModelClassPackageName () | 子类返回model类所在包名 |
| String classNameToCreateView (String viewClassPackageName, String simpleName) | 通过view类包名和当前类类名生成view的全限定名 |
| String classNameToCreateModel (String viewClassPackageName, String simpleName) | 通过model类包名和当前类类名生成model的全限定名 |

备注：最后6个方法为反射创建view和model实例所需要的方法，具体用法参见[部分功能说明3.Presenter反射创建说明](https://github.com/AcgnCodeMonkey/MVPLibrary/blob/master/file/special.md)

#### BaseFragmentPresenter
&emsp;&emsp;FragmentPresenter基类,此类方法和BaseActivityPresenter中基本相同，这里只列举不同的方法

|             方法名        | 说明           |
|:---:|:---:|
|View createLayout (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) |创建视图方法，与BaseActivityPresenter的参数列表稍有不同|
| void onInVisible () | fragment不可见时，会回调此方法 |
| void onVisible () | fragment可见时会回调此方法 |
| void lazyLoad () | 懒加载方法，需要懒加载的代码可以写在此方法中 |
| boolean isEveryReload () | 是否每次fragment从不可见变为可见状态时都重新进行懒加载 |
| void resetLoadingState () | 强制重置当前加载状态为未加载 |

---
### 其他部分类说明
#### BaseMvpHelper
&emsp;&emsp;model,view,presenter的helper类的基类

|             方法名        | 说明           |
|:---:|:---:|
| <T extends BaseHelper> void addHelper (@HelperType.HelperTypeEnum int type, T helper) | 为当前helper类扩展一个helper类到自己内部，并规定他的类型号 |
| <T extends BaseHelper> T getHelper (@HelperType.HelperTypeEnum int type) | 获取当前helper类中的某一个类型的扩展类对象 |
| void removeHelper (@HelperType.HelperTypeEnum int type) | 去除当前helper类中某一个类型的扩展类 |
| void clearHelpers () | 清空当前所有的扩展helper类 |
