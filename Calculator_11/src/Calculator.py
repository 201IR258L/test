'''
株式会社新日本企画教育事業部職業訓練
Webアプリ技術者育成科修了課題
電卓V2アプリ
作成者：第3教室11番　中島　栄作
提出日：2024年1月11日
'''
# 電卓本体　Calculator.py

# リストの形式変換機能
import ChangeLists
# 計算機能
import Calculation
# 正規表現操作
import re

# 空白文字
blankCharacter = '';
# 数字正規表現
number = r'\d+'
# クリアキー
clearKey = 'c'
# イコールキー
equalKey = '='
# 終了キー
exitKey = 'exit'
# 計算過程を格納するリスト
calculationProcess = []
# 数字以外の操作キー
operationKey = ['+', '-', '*', '/', '=', 'c', 'exit']


#停止条件が入力されない限り繰り返す
while True:
    # 入力ガイド
    inputCharacters = input('数字を入力してください。>')
    # 条件の間繰り返す
    #   条件⓵数字の正規表現不適合②終了キーではない③入力せずEnterKey
    while not (bool(re.fullmatch(number, inputCharacters)) or inputCharacters == exitKey)  or inputCharacters == blankCharacter:
        # 再入力
        inputCharacters = input('数字を入力してください。>')
    # exitを入力した場合
    if inputCharacters == exitKey:
        # 終了メッセージ表示
        print('計算を終了します。')
        # プログラム終了
        exit()
    # 数字を入力した場合
    else:
        # 計算過程リストに追加
        calculationProcess.append(inputCharacters)
        # 計算過程を画面に表示(数字と演算子のみ)
        print(*calculationProcess)
        # 演算子を入力
        inputCharacters = input('演算子を入力してください。>')
        # 条件の間繰り返す
        #   条件⓵操作キー以外入力(exit含む)②入力せずEnterKey
        while not inputCharacters in operationKey or inputCharacters == "":
            # 再入力
            inputCharacters = input('演算子を入力してください。>')
        # イコールキー入力
        if inputCharacters == equalKey:
            # 計算過程リスト追加
            calculationProcess.append(equalKey)
            # 計算過程画面表示(数字と演算子のみ)
            print(*calculationProcess)
            # 計算過程からイコールキー除去
            calculationProcess.remove(equalKey)
            # 計算過程リストを計算用文字列に変換
            calculationFormulaString = ''.join(calculationProcess)
            # 計算用文字列を計算用配列へ変換
            computationalArray = ChangeLists.getList(calculationFormulaString)
            # 配列を逆ポーランド記法の並びに変換
            reversePolishNotationOrderList = ChangeLists.createPL(computationalArray)
            # 並び替えた配列を1次元配列に変換
            oneDimensionalArray = ChangeLists.oneList(reversePolishNotationOrderList)
            # 正常処理
            try:
                # 計算と結果出力
                print(str(Calculation.calc(oneDimensionalArray)))
                # 計算過程クリア
                calculationProcess.clear()
            # 0割
            except ZeroDivisionError:
                # エラーメッセージ
                print('0割はできません。')
                # 計算過程クリア
                calculationProcess.clear()
                print('入力値をクリアしました。')
            # 予期しないエラー
            except:
                # エラーメッセージ
                print('予期せずエラーが発生しました。')
                # 計算過程クリア
                calculationProcess.clear()
                print('入力値をクリアしました。')
        # クリアキー
        elif inputCharacters == clearKey:
            # 計算過程クリア
            calculationProcess.clear()
            print('入力値をクリアしました。')
        # 終了キー
        elif inputCharacters == exitKey:
            # エラーメッセージ
            print('計算を終了します。')
            # システム終了
            exit()
        # 計算継続
        else:
            # 計算過程リスト追加
            calculationProcess.append(inputCharacters)
            # 計算過程画面表示(数字と演算子のみ)
            print(*calculationProcess)