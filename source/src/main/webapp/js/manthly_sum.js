//工数の計算
//解決済みのため保留でＯＫ
//document.querySelectorAll(".").forEach((el) => {
//  const= "";
//  if (el.textContent == "") {
//    el.textContent = ;
//  } else {
//    el.textContent = ;
//  }
//});

//集計ボタンを押した時に初めてデータを表示する
const button = document.getElementById('total');
const list = document.getElementById('data');
button.addEventListener('click', function(){
  list.style.display = 'block';
});