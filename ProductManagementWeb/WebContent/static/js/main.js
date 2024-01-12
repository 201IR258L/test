"use strict";

var words = [
  { key: "サーブレット", a: "Javaを用いてサーバサイドプログラムを実現する技術です。" },
  { key: "フォワード", a: "処理を別のサーブレットクラスやJSPファイルに転送する機能です。" },
  { key: "リダイレクト", a: "ブラウザのリクエスト先を変更して処理の転送を行います。" },
  { key: "プログラミングの上達の近道は", a: "第3教室で学ぶ。" }
];
var stock = [];

let card = document.getElementById("card");
let cardFront = document.getElementById("card-front");
let cardBack = document.getElementById("card-back");
let btn = document.getElementById("btn");
card.addEventListener("click", function() {
  flip();
});

btn.addEventListener("click", function() {
  next();
});

function flip() {
  card.className = card.className === "" ? "open" : "";
}

function next() {
  if (card.className === "open") {
    card.addEventListener("transitionend", setCard);
    flip();
  } else {
    setCard();
  }
}

function setCard() {
  var num = Math.floor(Math.random() * words.length);
  cardFront.innerHTML = words[num]["key"];
  cardBack.innerHTML = words[num]["a"];
  card.removeEventListener("transitioned", setCard);
}
