# JetpackLab

Android Jetpack コンポーネントの一部を使用してみる実験リポジトリです。  
* Navigationを使用してFragmentの切り替えを行ったFragmentLab
* Roomの使用を目的にDBへの追加とその追加をLiveDataで監視するMVVMモデルの実装を行ったRoomLab
* 【予定】（Dagger2を使用した依存性の注入)
* 【予定】（Retrofit or Apollo を使用したWebAPI通信の実装）

RoomLabに関しては、その大部分を以下のリンク先を参考にしています。  
[https://github.com/Tsutou/GithubClient](https://github.com/Tsutou/GithubClient)

### Navigation
[https://developer.android.com/guide/navigation/](https://developer.android.com/guide/navigation/)  
Fragment1,2,3を単純に遷移していく設定をNavigationを使用して実装しています。

### Room
[https://developer.android.com/topic/libraries/architecture/room](https://developer.android.com/topic/libraries/architecture/room)  
SQLiteに簡単にアクセスするライブラリ。  
アイテム名を入力して、登録すると、その下のリストが連動して追加されるよう監視しています。  
RoomLaboは合わせて以下のライブラリも使用しています。

* ViewModel
* LiveData

その他  
* DBアクセス時の非同期処理はKotlinのCoroutinを使用して実装
