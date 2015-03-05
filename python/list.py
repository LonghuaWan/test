#list 方法
name=list('Perl')
#分片 赋值
name[1:]=list('ar')
#分片插入
numbers = [1,5]
numbers[1:1] = [2,3,4]
#分片删除
numbers[1:3] = []

#############列表方法###########
#append方法
lst = [1,2,3]
lst.append(4)

#count方法
x= [[1,2],1,1,[2,1,[1,2]]]
x.count(1)

#extend方法,新列表扩展原有列表，修改了原始序列，链接操作不修改原始序列
a = [1,2,3]
b = [4,5,6]
a.extend(b)
#使用分片实现extend效果
a[len(a):] = b


#index方法
kn = ['we','are','the']
kn.index('are')

#insert 方法
kn.insert(2,'good')

#pop方法　移出列表最后一个元素，并返回该值,可以使用extend实现入栈 pop出栈
kn.pop()

#remove方法 移除列表中第一个匹配项
kn.remove('we')

#reverse 反转列表元素
kn.reverse()

#sort方法
x = [4,6,2,1,7,9]
x.sort()

#sorted 方法获取排序后列表副本
sorted(x)

#列表复制
y = x[:]

#高级排序　通过自定义compare(x,y)方法　类似与java中的compator,python实现了内建的cmp cmp(x,y) x<y 返回负数　＝时返回零　x>y返回正数
x.sort(cmp)

#通过sort的两个可选参数 key 或 reverse实现高级排序
x = ['aadfsdf','adf','adfsd','adsads']
x.sort(key=len)#按元素长度排序

x = [4,6,2,1,7,9]
x.sort(reverse=True)#为TRUE时反转
#cmp key reverse都可以用于sorted函数，为cmp key提供自定义函数很有用

#元组不可修改　创建元组使用逗号　和　括号　或者只用逗号　空元组只能用括号

#tuple函数将序列转化为元组
tuple([1,2,3])
#元组可分片,分片后还是元组


