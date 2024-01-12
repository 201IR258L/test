let canvas = document.getElementById("sunGodCanvas");
    if(canvas.getContext){
        let ctx = sunGodCanvas.getContext("2d");
        //全体の透明度を定義。
        ctx.globalAlpha = 0.4;

        for(i=0; i < 1000; i++) {
            ctx.beginPath ();
            let r= random(256);//ランダムな色
            let g= random (256);
            let b= random (256);
            ctx.strokeStyle='rgb('+r+','+g+','+b+')';//文字列連結

            ctx.moveTo(random(1500), random(800));//ランダムな位置から
            ctx.lineTo(random(1500), random(800));//ランダムな位置へ
            ctx.stroke();//線を引く     
        }
        function random (number) {
            return Math.floor(Math.random ()* (number+1));
        }
    }