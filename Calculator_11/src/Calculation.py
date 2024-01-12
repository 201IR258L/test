# 逆ポーランド記法に並び替えられた配列を用いて計算を行う
def calc(linearArray):
    # 計算用配列
    stackList=[]
    
    for t in linearArray:
        #演算子が読み込まれた場合,number2<演算子>number1の計算結果をstackListに格納する
        if t=='+':
            number1=stackList.pop()
            number2=stackList.pop()
            stackList.append(number2+number1)
        elif t=='-':
            number1=stackList.pop()
            number2=stackList.pop()
            stackList.append(number2-number1)
        elif t=='*':
            number1=stackList.pop()
            number2=stackList.pop()
            stackList.append(number2*number1)
        elif t=='/':
            number1=stackList.pop()
            number2=stackList.pop()
            # 割った商の整数部分を得る
            stackList.append(number2//number1)
        else:
        # 数字の場合はそのままstackListに格納する。
            stackList.append(t)
    # 計算用配列を返す
    return stackList.pop()