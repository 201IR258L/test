# リスト変換機能
def getList(text):
    #スペース除去
    result = text.replace(' ','')
    MFlist = []
    number = 0
    
    #整数を追加するためのフラグ
    addTrigger= False
    
    #整数以外で入力可能な文字のリスト
    mylist=['(',')','^','*','/','+','-','%']
    
    #1文字ずつ読み込んでいく
    for t in result:
        if t.isdecimal():
            addTrigger=True
            number=number*10+int(t)
        else:
            #演算子の手前に数字があった場合は先に数字を追加した後に演算子を追加する
            if addTrigger:
                MFlist.append(number)
                addTrigger=False
                number=0
                if t in mylist:
                    MFlist.append(t)
            elif t in mylist:
                MFlist.append(t)
    #最後が数字で終わっている場合はここで追加する
    if result[-1].isdecimal():
        MFlist.append(number)
    return MFlist

#配列を逆ポーランド記法の並びに変換する
def createPL(prelist):
    end=len(prelist)
    #-2のような符号反転がある場合は0-2のように変換を行う
    if prelist[0]=='-':
        prelist.insert(0, 0)
    
    #括弧の中身を先に逆ポーランド記法の並びに変換していく
    for i, j in enumerate(prelist):
        if j=='(':
            parenthesisNumber = 0
            for h in range(i+1,end):
                if prelist[h] == ')':
                    if parenthesisNumber == 0:
                        golist=prelist[i+1:h]
                        del prelist[i:h+1]
                        prelist.insert(i,createPL(golist))
                        break
                    else:
                        parenthesisNumber -= 1
                elif prelist[h]=='(':
                    parenthesisNumber += 1
    
    #指数がある項を変換していく
    i = 0
    while i < len(prelist):
        if prelist[i]=='^':
            operationList= []
            operationList.append(prelist[i-1])
            operationList.append(prelist[i+1])
            operationList.append(prelist[i])
            del prelist[i-1:i+2]
            prelist.insert(i-1,operationList)
            i -= 1
        i += 1
    
    #乗算除算剰余がある項を変換していく
    i = 0
    while i < len(prelist):
        if prelist[i]=='*'or prelist[i]=='/'  or prelist[i]=='%':
            operationList= []
            operationList.append(prelist[i-1])
            operationList.append(prelist[i+1])
            operationList.append(prelist[i])
            del prelist[i-1:i+2]
            prelist.insert(i-1,operationList)
            i -= 1
        i += 1

    #加算減算がある項を変換していく
    i = 0
    while i < len(prelist):
        if prelist[i]=='+'or prelist[i]=='-':
            operationList= []
            operationList.append(prelist[i-1])
            operationList.append(prelist[i+1])
            operationList.append(prelist[i])
            del prelist[i-1:i+2]
            prelist.insert(i-1,operationList)
            i -= 1
        i += 1
    
    return prelist

#並び終えた配列を1次元配列として返す
def oneList(lists):
    #変換先の1次元配列
    outputList=[]
    for t in lists:
        #配列の要素tが配列の場合、配列tの中身を配列outputListに加えていく
        if isinstance(t, list):
            outputList+=oneList(t)
        else:
            outputList.append(t)
    return outputList