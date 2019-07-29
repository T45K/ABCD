[![CircleCI](https://circleci.com/gh/T45K/ABCD.svg?style=svg)](https://circleci.com/gh/T45K/ABCD) [![codecov](https://codecov.io/gh/T45K/ABCD/branch/master/graph/badge.svg)](https://codecov.io/gh/T45K/ABCD)

# ABCD
ブロック単位でのコードクローン（重複コード）を検出するツールです．<br>
Javaのソースコードに対して実行することができます．<br>
現在，タイプ1とタイプ2のクローンに加え，独自に定義したExtract Method リファクタリングを行いやすいようなクローンのみを検出することができます．

## オプション
`-s` は必ず指定してください．

|オプション名|エイリアス|内容|初期値| 
|:---:|:---:|:---:|:---:|
|-s|--srcDir|クローンを検出したいソースコードが含まれているディレクトリのパス|なし|
|-d|--detection-mode|検出したいクローンタイプ<br>1: タイプ1，2: タイプ2，3: オリジナル|タイプ2|
|-o|--output-file|出力したいファイル名．ディレクトリも含めて指定することができます| `./output`|
|-f|--format|出力ファイルの形式<br>json，csv，xml or txt|json|
|-b|--binary-dir|検出対象のクラスファイルが含まれるディレクトリのパス<br>オリジナルタイプのクローンを検出するときに必要|なし|
|-l|--lib-dir|検出対象が依存しているjarファイルが含まれるディレクトリのパス<br>オリジナルタイプのクローンを検出するときに必要|なし|
|-tl|--threshold-line|検出するクローンの行数の閾値|5|
|-tt|--threshold-token|検出するクローンのトークン数の閾値|20|
