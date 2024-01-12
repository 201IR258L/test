let Engine = Matter.Engine,
  Render = Matter.Render,
  Runner = Matter.Runner,
  World = Matter.World,
  Bodies = Matter.Bodies;
// create engine
let engine = Engine.create(), //物理演算エンジン
  world = engine.world;
// create renderer
let render = Render.create ({ //レンダリング
    element: document.body,
    engine: engine,
    options: {
    width: 3000, //幅
    height:5000, //高さ
    background: '#eeeeee', //背景色
    wireframes: false
    }
});

//図形作成
let s1 = Bodies.circle (100, 800, 30,{frictionAir: 0.01});
let s2 = Bodies. rectangle (150, 800, 80, 80,{frictionAir: 0.02});
let s3 = Bodies.circle (200, 800, 40,{frictionAir: 0.03}) ;
let s4 = Bodies.circle (300, 800, 20,{frictionAir: 0.03}) ;
let s5 = Bodies.circle (500, 800, 30,{frictionAir: 0.04}) ;
let s6 = Bodies.circle (600, 800, 30,{frictionAir: 0.04}) ;
let s7 = Bodies.circle (700, 800, 30,{frictionAir: 0.04}) ;
let s8 = Bodies.circle (800, 800, 30,{frictionAir: 0.04}) ;
let s9 = Bodies.circle (900, 800, 30,{frictionAir: 0.04}) ;
let s10 = Bodies. rectangle (1000, 800, 80, 80,{frictionAir: 0.04});
let s11 = Bodies.circle (1100, 1000, 30,{frictionAir: 0.03});
let s12 = Bodies.circle (1200, 800, 30,{frictionAir: 0.01});
let s13 = Bodies. rectangle (1300, 800, 80, 80,{frictionAir: 0.02});
let s14 = Bodies.circle (1400, 1100, 40,{frictionAir: 0.03}) ;
let s15 = Bodies.circle (1500, 1100, 20,{frictionAir: 0.03}) ;
let s16 = Bodies.circle (1600, 1100, 30,{frictionAir: 0.04}) ;
let s17 = Bodies. rectangle (1700, 1100, 80, 80,{frictionAir: 0.04});
let s18 = Bodies.circle (1800, 1100, 30,{frictionAir: 0.03});
let s19 = Bodies. rectangle (1900, 1100, 80, 80,{frictionAir: 0.01});
let s20 = Bodies.circle (2000, 1300, 40,{frictionAir: 0.02}) ;
let s21 = Bodies.circle (2100, 1400, 20,{frictionAir: 0.03}) ;
let s22 = Bodies.circle (2200, 1420, 30,{frictionAir: 0.06}) ;
let s23 = Bodies. rectangle (2300, 1420, 80, 80,{frictionAir: 0.07});
let s24 = Bodies.circle (2400, 1420, 30,{frictionAir: 0.06}) ;
let s25 = Bodies. rectangle (2700, 1420, 80, 80,{frictionAir: 0.07});
let s26 = Bodies.circle (100, 1500, 30,{frictionAir: 0.01});
let s27 = Bodies. rectangle (150, 1500, 80, 80,{frictionAir: 0.02});
let s28 = Bodies.circle (200, 1500, 40,{frictionAir: 0.03}) ;
let s29 = Bodies.circle (300, 1500, 20,{frictionAir: 0.03}) ;
let s30 = Bodies.circle (500, 1500, 30,{frictionAir: 0.04}) ;
let s31 = Bodies.circle (600, 1500, 30,{frictionAir: 0.04}) ;
let s32 = Bodies.circle (700, 1500, 30,{frictionAir: 0.04}) ;
let s33 = Bodies.circle (800, 1500, 30,{frictionAir: 0.04}) ;
let s34 = Bodies.circle (900, 1500, 30,{frictionAir: 0.04}) ;
let s35 = Bodies. rectangle (1000, 1500, 80, 80,{frictionAir: 0.04});
let s36 = Bodies.circle (1100, 1500, 30,{frictionAir: 0.03});
let s37 = Bodies.circle (1200, 1600, 30,{frictionAir: 0.01});
let s38 = Bodies. rectangle (1300, 1700, 80, 80,{frictionAir: 0.02});
let s39 = Bodies.circle (1400, 1800, 40,{frictionAir: 0.03}) ;
let s40 = Bodies.circle (1500, 1400, 20,{frictionAir: 0.03}) ;
let s41 = Bodies.circle (1600, 1900, 30,{frictionAir: 0.04}) ;
let s42 = Bodies. rectangle (1700, 1500, 80, 80,{frictionAir: 0.04});
let s43 = Bodies.circle (1800, 1200, 30,{frictionAir: 0.03});
let s44 = Bodies. rectangle (1900, 2000, 80, 80,{frictionAir: 0.01});
let s45 = Bodies.circle (2000,2100, 40,{frictionAir: 0.02}) ;
let s46 = Bodies.circle (2100, 2200, 20,{frictionAir: 0.03}) ;
let s47 = Bodies.circle (2200,2300, 30,{frictionAir: 0.06}) ;
let s48 = Bodies. rectangle (2300, 2400, 80, 80,{frictionAir: 0.07});
let s49 = Bodies.circle (2400, 1800, 30,{frictionAir: 0.06}) ;
let s50 = Bodies. rectangle (2700, 2000, 80, 80,{frictionAir: 0.07});
let s51 = Bodies.circle (1000, 2000, 800,{frictionAir: 0.06}) ;
World.add(world, [ //図形を追加
s1,
s2,
s3,
s4,
s5,
s6,
s7,
s8,
s9,
s10,
s11,
s12,
s13,
s14,
s15,
s16,
s17,
s18,
s19,
s20,
s21,
s22,
s23,
s24,
s25,
s26,
s27,
s28,
s29,
s30,
s31,
s32,
s33,
s34,
s35,
s36,
s37,
s38,
s39,
s40,
s41,
s42,
s43,
s44,
s45,
s46,
s47,
s48,
s49,
s50,
s51
]);
engine.world.gravity.y = -1;
Render.run (render) ;
Engine.run (engine) ;