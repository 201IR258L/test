/* ---------------------------------------------------------------------
	アナログ時計
 --------------------------------------------------------------------- */
/*
 * グローバル変数
 */
let wrapper = null;				// キャンバスの親要素
let canvas = null;					// キャンバス
let g = null;						// コンテキスト
let clock_size;					// 時計のサイズ
let scale;							// 時計のスケール
let center = { x:0, y:0 };			// 時計の中心座標
let $id = function(id){ return document.getElementById(id); };	// DOM取得用

/*
 * 定数
 */
const BACKGROUND_COLOR = "skyblue";		// 背景色
const WAKU_COLOR = "yellow";		// 時計枠の色
const CLOCK_BG_COLOR = "white";			// 時計枠内側の色
const CLOCK_CENTER_COLOR = "pink";	// 時計針の中心のピンの色
const MOJI_BAN_COLOR = "blue";			// 文字盤の12本の線の色
const SUJI_COLOR = "black"				// 数字の色
const JI_SHIN_COLOR = "green";			// 時針の色
const FUN_SHIN_COLOR = "red";			// 分針の色
const BYOU_SHIN_COLOR = "deeppink";		// 秒針の色

/*
 * キャンバスのサイズをウインドウに合わせて変更
 */
function getSize(){
	// キャンバスのサイズを再設定
	canvas.width = wrapper.offsetWidth;
	canvas.height =  wrapper.offsetHeight;
	// キャンバスの中心を設定
	center.x = canvas.width / 2;
	center.y = canvas.height / 2;
	// 短辺を時計のサイズとする
	clock_size = canvas.width >= canvas.height ? canvas.height : canvas.width;
	// 時計の縮尺を設定（ウインドウ短辺=500px のとき 縮尺=1 とする）
	scale = clock_size / 500.0;
}

/*
 * リサイズ時（キャンバスの中心と時計の縮尺を再設定）
 */
window.addEventListener("resize", function(){
	getSize();
});

/*
 * アナログ時計を描画する
 */
function clock(){
	g.save();		// デフォルト設定保存

	// 背景色を描画
	g.fillStyle = BACKGROUND_COLOR;
	g.fillRect(0, 0, canvas.width, canvas.height);

	// 時計枠の内側（背景色）を描画
	g.translate(center.x, center.y);		// キャンバスの中心を中心座標に設定
	g.scale(scale, scale);				// 時計の縮尺を設定
	g.fillStyle = CLOCK_BG_COLOR;
	g.beginPath();
	g.arc(0, 0, 200, 0, Math.PI*2, true);
	g.fill();

	// 時計枠の描画
	g.beginPath();
	g.lineWidth = 25;
	g.strokeStyle = WAKU_COLOR;
	g.arc(0, 0, 200, 0, Math.PI*2, true);
	g.stroke();

	g.rotate(-Math.PI/2);	// 左に90度回転（12時方向を0度とするため）
	g.lineCap = "round";	// 時針、分針、秒針の角をを丸くするため設定

	// 現在時刻取得
	let now = new Date();

	let hour = now.getHours();			// 時
	let minute = now.getMinutes();		// 分
	let second = now.getSeconds();		// 秒

	hour = hour >= 12 ? hour - 12 : hour;	// 13時～24時  -> 1時～12時に変更

	// 文字盤の時間を表す12本の線を描画
	g.save();
	g.strokeStyle = MOJI_BAN_COLOR;
	g.lineWidth = 4;

	g.beginPath();
	for(let i=0; i<12; i++){
		g.rotate(Math.PI/6);	// 30度ずつ回転
		g.moveTo(170, 0);
		g.lineTo(180, 0);
	}
	g.stroke();
	g.restore();

	// 時間の数字を描画
	g.save();
	g.rotate(Math.PI/2);		// 回転を元に戻す（3時15分方向を0度）
							// 文字盤の数字の描画向きが傾いてしまう為
	g.fillStyle = SUJI_COLOR;
	g.font = "bold 32px sans-serif";	// ゴシック体
	g.textBaseline = "middle";

	let angle = -60;	// 時計中心からの角度（数字の1から描画開始）
	let offset = [10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 15, 20];	// 数字によるx座標のずれを補正
	let r = 150;		// 時計中心からの半径

	i = 1;
	while(i <= 12){
		let radian = angle * Math.PI / 180;	// ラジアンに変換
		let x = r * Math.cos(radian);		// x座標
		let y = r * Math.sin(radian);		// y座標

		if(i % 3 == 0) g.fillText(i, x-offset[i-1], y);	// 3, 6, 9, 12のみ描画
		angle += 30;
		i++;
	}
	g.restore();

	// 時針を描画
	g.save();
	g.rotate( hour * (Math.PI/6) + minute * (Math.PI/360) + second * (Math.PI/21600) );	// 時数*30度 + 分数*0.5度 + 秒数 * 0.00833333333度 回転
	g.lineWidth = 12;
	g.strokeStyle = JI_SHIN_COLOR;
	g.beginPath();
	g.moveTo(-20, 0);
	g.lineTo(85, 0);
	g.stroke();
	g.restore();

	// 分針を描画
	g.save();
	g.rotate( minute * (Math.PI/30) + second * (Math.PI/1800) );	// 分数*6度 + 秒数*0.1度 回転
	g.lineWidth = 8;
	g.strokeStyle = FUN_SHIN_COLOR;
	g.beginPath();
	g.moveTo(-28, 0);
	g.lineTo(112, 0);
	g.stroke();
	g.restore();

	// 秒針を描画
	g.rotate(second * Math.PI/30);	// 秒数*6度 回転
	g.strokeStyle = BYOU_SHIN_COLOR;
	g.lineWidth = 4;
	g.beginPath();
	g.moveTo(-30, 0);
	g.lineTo(105, 0);
	g.stroke();

	// 時計の中心を描画
	g.fillStyle = CLOCK_CENTER_COLOR;
	g.beginPath();
	g.arc(0, 0, 8, 0, Math.PI*2, true);
	g.fill();

	g.restore();

	setTimeout(clock, 1000);	// 再帰呼び出し
}

/*
 * 起動時の処理
 */
window.addEventListener("load", function(){
	// キャンバスの親要素情報取得（親要素が無いとキャンバスのサイズが画面いっぱいに表示できないため）
	wrapper = $id("wrapper");
	// キャンバス情報取得
	canvas = $id("clock");
	g = canvas.getContext("2d");

	// キャンバスをウインドウサイズにする
	getSize();

	// アナログ時計を起動
	clock();
});
