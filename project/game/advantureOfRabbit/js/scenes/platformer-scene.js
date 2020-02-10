import Phaser from "phaser";
import Player from "../sprite-objects/player.js";
import Marker from "../game-objects/marker.js";
import Exit from "../game-objects/next-exit.js";
import Robot from "../sprite-objects/enemy-robot.js";
import Dog from "../sprite-objects/enemy-dog.js";
import Mazayong from "../sprite-objects/enemy-mazayong.js";
import Slime from "../sprite-objects/enemy-slime.js";
import Wood from "../game-objects/wood.js";
import Arrow from "../game-objects/arrow.js";
//import Jquery from "../object/jquery.js";

// scene index를 위한 변수
var a = 0;
// 상자개수 초기화 [게임시작, 다음단계 넘어갈때-별이랑 오버랩, 죽었을 때] - map에 적힌 숫자대로 수정
var boxList = [0, 10, 5, 15, 3, 3, 20, 5, 12, 0, 10, 3, 0, 0, 0, 0];
var box = boxList[a];
// 클릭 여부 파악을 위한 변수
var pointer;
// 클릭시 위치를 담는 변수
var worldPoint;
// 새로 생성된 box의 좌표를 담는 변수
var nowPoint;
// tilemaps 이름 리스트
var mapList = [
  "./assets/tilemaps/stage1-1_b0.json",
  "./assets/tilemaps/stage1-2_b10.json",
  "./assets/tilemaps/stage1-3_b5.json",
  "./assets/tilemaps/stage1-4_b15.json",
  "./assets/tilemaps/stage1-5_b3.json",
  "./assets/tilemaps/stage2-1_b3.json",
  "./assets/tilemaps/stage2-2_b20.json",
  "./assets/tilemaps/stage2-3_b5.json",
  "./assets/tilemaps/stage2-4_b12.json",
  "./assets/tilemaps/stage3-1_b0.json",
  "./assets/tilemaps/stage3-2_b10.json",
  "./assets/tilemaps/stage3-3_b3.json",
  "./assets/tilemaps/stage3-4_b0.json",
  "./assets/tilemaps/stage4-1_b0.json",
  "./assets/tilemaps/stage4-2.json",
  "./assets/tilemaps/stage4-3_b0.json"
];
// 플레이 시간 표기를 위한 텍스트박스
var timeText;
// 총 플레이 시간을 담기위한 변수
var totalTime = 0;
var hide;
var carrot;

// 장작 개수
var woodNum = 0;

// 배경음악 리스트
var audioList = [
  "../assets/audio/04.mp3",
  "../assets/audio/02.mp3",
  "../assets/audio/03.mp3",
  "../assets/audio/01.mp3"
];

// 배경음악 재생위한 변수
var i = 0;

export default class PlatformerScene extends Phaser.Scene {
  saveTime() {
    //console.log(totalTime);
    //ajax 통신, totalTime html 페이지로 전달
    if (a == 1) {
      var id = document.getElementById("totalTime");
      id.value = totalTime;
    }
  }

  preload() {
    this.load.spritesheet("player", "../assets/spritesheets/player.png", {
      frameWidth: 34,
      frameHeight: 34
    });
    this.load.spritesheet("enemy", "../assets/spritesheets/enemy.png", {
      frameWidth: 34,
      frameHeight: 34
    });

    this.load.spritesheet("fire", "../assets/spritesheets/fire.png", {
      frameWidth: 34,
      frameHeight: 34
    });

    this.load.spritesheet("wood", "../assets/spritesheets/wood.png", {
      frameWidth: 34,
      frameHeight: 34
    });

    this.load.spritesheet("arrow", "../assets/spritesheets/arrow.png", {
      frameWidth: 34,
      frameHeight: 34
    });

    this.load.image("spike", "../assets/images/spike.png");
    this.load.image("star", "../assets/images/star.png");
    this.load.image("tiles", "./assets/tilesets/tileset.png");
    this.load.image("hide", "./assets/images/hide.png");
    this.load.image("water", "./assets/images/water.png");
    this.load.image("magma", "./assets/images/magma.png");

    this.load.tilemapTiledJSON("map" + a, mapList[a]);

    // 스테이지에 따라 audioList에서 다른 곡을 재생하기 위한 변수
    if (a === 0) {
      i = 0;
    } else if (a === 1) {
      i = 1;
    } else if (a === 2) {
      i = 2;
    }

    // 재생할 음악을 로드
    this.load.audio("audio" + a, audioList[i]);

    // *효과음 로드 - 시도중
    this.load.audio("death", "[../assets/audio/death.wav]");
    this.load.audio("kill", "[../assets/audio/kill.wav]");
    this.load.audio("next", "[../assets/audio/next.wav]");
  }

  create() {
    //ajax 통신 시도중
    //this.jquery = new Jquery();

    // 로드한 배경음악 객체 생성 및 재생
    this.music = this.sound.add("audio" + i);
    this.music.play();

    this.isPlayerDead = false;
    this.isNext = false;

    const map = this.make.tilemap({ key: "map" + a });
    const tiles = map.addTilesetImage("tileset", "tiles");

    map.createDynamicLayer("Background", tiles);
    this.groundLayer = map.createDynamicLayer("Ground", tiles);

    // 타일맵에서 지정한 object 위치에 캐릭터를 띄우기
    const point = map.findObject("Object", obj => obj.name === "Point");
    this.player = new Player(this, point.x, point.y);

    // 다음 스테이지 출구를 위한 객체 생성
    const exit = map.findObject("Object", obj => obj.name === "Next");
    this.next = new Exit(this, exit.x, exit.y);
    this.next.exit.setVisible(false);

    // 모든 enemy를 담기 위한 그룹 생성
    this.enemyGroup = this.physics.add.staticGroup();

    // 장작 object 객체 생성
    this.w1 = map.findObject("Object", obj => obj.name === "W1");
    this.w2 = map.findObject("Object", obj => obj.name === "W2");
    this.w3 = map.findObject("Object", obj => obj.name === "W3");
    this.w4 = map.findObject("Object", obj => obj.name === "W4");
    this.w5 = map.findObject("Object", obj => obj.name === "W5");
    this.w6 = map.findObject("Object", obj => obj.name === "W6");
    this.w7 = map.findObject("Object", obj => obj.name === "W7");
    this.w8 = map.findObject("Object", obj => obj.name === "W8");
    this.w9 = map.findObject("Object", obj => obj.name === "W9");
    this.w10 = map.findObject("Object", obj => obj.name === "W10");

    // 화살표 object 객체 생성
    this.l1 = map.findObject("Object", obj => obj.name === "L1");
    this.l2 = map.findObject("Object", obj => obj.name === "L2");
    this.l3 = map.findObject("Object", obj => obj.name === "L3");

    this.r1 = map.findObject("Object", obj => obj.name === "R1");
    this.r2 = map.findObject("Object", obj => obj.name === "R2");
    this.r3 = map.findObject("Object", obj => obj.name === "R3");

    this.u1 = map.findObject("Object", obj => obj.name === "U1");
    this.u2 = map.findObject("Object", obj => obj.name === "U2");
    this.u3 = map.findObject("Object", obj => obj.name === "U3");

    this.d1 = map.findObject("Object", obj => obj.name === "D1");
    this.d2 = map.findObject("Object", obj => obj.name === "D2");
    this.d3 = map.findObject("Object", obj => obj.name === "D3");

    // 화살표 객체 생성
    if (this.l1 !== null) {
      this.left1 = new Arrow(this, this.l1.x, this.l1.y, "left");
    }
    if (this.l2 !== null) {
      this.left2 = new Arrow(this, this.l2.x, this.l2.y, "left");
    }
    if (this.l3 !== null) {
      this.left3 = new Arrow(this, this.l3.x, this.l3.y, "left");
    }

    if (this.r1 !== null) {
      this.right1 = new Arrow(this, this.r1.x, this.r1.y, "right");
    }
    if (this.r2 !== null) {
      this.right2 = new Arrow(this, this.r2.x, this.r2.y, "right");
    }
    if (this.r3 !== null) {
      this.right3 = new Arrow(this, this.r3.x, this.r3.y, "right");
    }

    if (this.u1 !== null) {
      this.up1 = new Arrow(this, this.u1.x, this.u1.y, "up");
    }
    if (this.u2 !== null) {
      this.up2 = new Arrow(this, this.u2.x, this.u2.y, "up");
    }
    if (this.u3 !== null) {
      this.up3 = new Arrow(this, this.u3.x, this.u3.y, "up");
    }

    if (this.d1 !== null) {
      this.down1 = new Arrow(this, this.d1.x, this.d1.y, "down");
    }
    if (this.d2 !== null) {
      this.down2 = new Arrow(this, this.d2.x, this.d2.y, "down");
    }
    if (this.d3 !== null) {
      this.down3 = new Arrow(this, this.d3.x, this.d3.y, "down");
    }

    // object 레이어에서 *_start 객체 찾아서 생성
    this.robot1_start = map.findObject(
      "Object",
      obj => obj.name === "Robot1_start"
    );
    this.dog1_start = map.findObject(
      "Object",
      obj => obj.name === "Dog1_start"
    );
    this.mazayong1_start = map.findObject(
      "Object",
      obj => obj.name === "Mazayong1_start"
    );
    this.slime1_start = map.findObject(
      "Object",
      obj => obj.name === "Slime1_start"
    );

    this.robot2_start = map.findObject(
      "Object",
      obj => obj.name === "Robot2_start"
    );
    this.dog2_start = map.findObject(
      "Object",
      obj => obj.name === "Dog2_start"
    );
    this.mazayong2_start = map.findObject(
      "Object",
      obj => obj.name === "Mazayong2_start"
    );
    this.slime2_start = map.findObject(
      "Object",
      obj => obj.name === "Slime2_start"
    );

    this.robot3_start = map.findObject(
      "Object",
      obj => obj.name === "Robot3_start"
    );
    this.dog3_start = map.findObject(
      "Object",
      obj => obj.name === "Dog3_start"
    );
    this.mazayong3_start = map.findObject(
      "Object",
      obj => obj.name === "Mazayong3_start"
    );
    this.slime3_start = map.findObject(
      "Object",
      obj => obj.name === "Slime3_start"
    );

    // 좌표 전달을 위하여 *_end 객체 생성
    this.robot1_end = map.findObject(
      "Object",
      obj => obj.name === "Robot1_end"
    );
    this.dog1_end = map.findObject("Object", obj => obj.name === "Dog1_end");
    this.mazayong1_end = map.findObject(
      "Object",
      obj => obj.name === "Mazayong1_end"
    );
    this.slime1_end = map.findObject(
      "Object",
      obj => obj.name === "Slime1_end"
    );

    this.robot2_end = map.findObject(
      "Object",
      obj => obj.name === "Robot2_end"
    );
    this.dog2_end = map.findObject("Object", obj => obj.name === "Dog2_end");
    this.mazayong2_end = map.findObject(
      "Object",
      obj => obj.name === "Mazayong2_end"
    );
    this.slime2_end = map.findObject(
      "Object",
      obj => obj.name === "Slime2_end"
    );

    this.robot3_end = map.findObject(
      "Object",
      obj => obj.name === "Robot3_end"
    );
    this.dog3_end = map.findObject("Object", obj => obj.name === "Dog3_end");
    this.mazayong3_end = map.findObject(
      "Object",
      obj => obj.name === "Mazayong3_end"
    );
    this.slime3_end = map.findObject(
      "Object",
      obj => obj.name === "Slime3_end"
    );

    // 장작 객체 생성
    if (this.w1 !== null) {
      this.wood1 = new Wood(this, this.w1.x, this.w1.y);
      // 장작 객체가 생성될 때만 장작 갯수 +1 설정
      woodNum += 1;
    }
    if (this.w2 !== null) {
      this.wood2 = new Wood(this, this.w2.x, this.w2.y);
      woodNum += 1;
    }
    if (this.w3 !== null) {
      this.wood3 = new Wood(this, this.w3.x, this.w3.y);
      woodNum += 1;
    }
    if (this.w4 !== null) {
      this.wood4 = new Wood(this, this.w4.x, this.w4.y);
      woodNum += 1;
    }
    if (this.w5 !== null) {
      this.wood5 = new Wood(this, this.w5.x, this.w5.y);
      woodNum += 1;
    }
    if (this.w6 !== null) {
      this.wood6 = new Wood(this, this.w6.x, this.w6.y);
      woodNum += 1;
    }
    if (this.w7 !== null) {
      this.wood7 = new Wood(this, this.w7.x, this.w7.y);
      woodNum += 1;
    }
    if (this.w8 !== null) {
      this.wood8 = new Wood(this, this.w8.x, this.w8.y);
      woodNum += 1;
    }
    if (this.w9 !== null) {
      this.wood9 = new Wood(this, this.w9.x, this.w9.y);
      woodNum += 1;
    }
    if (this.w10 !== null) {
      this.wood10 = new Wood(this, this.w10.x, this.w10.y);
      woodNum += 1;
    }

    // 객체 있다면 robot1_start 위치에 robot1 생성
    if (this.robot1_start !== null) {
      this.robot1 = new Robot(this, this.robot1_start.x, this.robot1_start.y);
      // 생성된 dog1 객체를 enemyGroup에 담음
      this.enemyGroup.add(this.robot1.robot);
    }

    if (this.robot2_start !== null) {
      this.robot2 = new Robot(this, this.robot2_start.x, this.robot2_start.y);
      this.enemyGroup.add(this.robot2.robot);
    }

    if (this.robot3_start !== null) {
      this.robot3 = new Robot(this, this.robot3_start.x, this.robot3_start.y);
      this.enemyGroup.add(this.robot3.robot);
    }

    if (this.dog1_start !== null) {
      this.dog1 = new Dog(this, this.dog1_start.x, this.dog1_start.y);
      this.enemyGroup.add(this.dog1.dog);
    }

    if (this.dog2_start !== null) {
      this.dog2 = new Dog(this, this.dog2_start.x, this.dog2_start.y);
      this.enemyGroup.add(this.dog2.dog);
    }

    if (this.dog3_start !== null) {
      this.dog1 = new Dog(this, this.dog3_start.x, this.dog3_start.y);
      this.enemyGroup.add(this.dog3.dog);
    }

    if (this.mazayong1_start !== null) {
      this.mazayong1 = new Mazayong(
        this,
        this.mazayong1_start.x,
        this.mazayong1_start.y
      );
      this.enemyGroup.add(this.mazayong1.mazayong);
    }

    if (this.mazayong2_start !== null) {
      this.mazayong2 = new Mazayong(
        this,
        this.mazayong2_start.x,
        this.mazayong2_start.y
      );
      this.enemyGroup.add(this.mazayong2.mazayong);
    }

    if (this.mazayong3_start !== null) {
      this.mazayong3 = new Mazayong(
        this,
        this.mazayong3_start.x,
        this.mazayong3_start.y
      );
      this.enemyGroup.add(this.mazayong3.mazayong);
    }

    if (this.slime1_start !== null) {
      this.slime1 = new Slime(this, this.slime1_start.x, this.slime1_start.y);
      this.enemyGroup.add(this.slime1.slime);
    }

    if (this.slime2_start !== null) {
      this.slime2 = new Slime(this, this.slime2_start.x, this.slime2_start.y);
      this.enemyGroup.add(this.slime2.slime);
    }

    if (this.slime3_start !== null) {
      this.slime3 = new Slime(this, this.slime3_start.x, this.slime3_start.y);
      this.enemyGroup.add(this.slime3.slime);
    }

    // enemyGroup에 충돌이벤트 생성
    this.physics.world.addCollider(this.enemyGroup, this.groundLayer);

    // 타일과 객체간의 충돌이벤트 걸기
    this.groundLayer.setCollisionByProperty({ cd: true });
    this.physics.world.addCollider(this.player.sprite, this.groundLayer);
    this.physics.world.addCollider(this.next.exit, this.groundLayer);

    // ** enemy 객체 생성전에 충돌이벤트를 선언할 경우 오류 발생.
    // (존재하지 않는 객체의 이벤트므로)
    // --> 따라서 enemy 객체가 생성되는 if문 안에서 충돌이벤트 선언.

    // spike 타일 이미지 사이즈에 맞게 닿을 수 있도록 만들기
    this.spikeGroup = this.physics.add.staticGroup();
    this.groundLayer.forEachTile(tile => {
      // 화살표 무명함수

      // spike 타일에 해당하는 타일들의 위치를 찾아서
      if (tile.index === 77) {
        const spike = this.spikeGroup.create(
          tile.getCenterX(),
          tile.getCenterY(),
          "spike"
        );

        // 방향 전환되는 타일들 있으므로, 확인해서 방향 전환까지 매치 시켜줌
        spike.rotation = tile.rotation;
        if (spike.angle === 0) spike.body.setSize(32, 6).setOffset(0, 26);
        else if (spike.angle === -90)
          spike.body.setSize(6, 32).setOffset(26, 0);
        else if (spike.angle === 90) spike.body.setSize(6, 32).setOffset(0, 0);
        this.groundLayer.removeTileAt(tile.x, tile.y);
      }
    });

    //동그라미 타일 변경
    this.hideGroup = this.physics.add.staticGroup();
    this.groundLayer.forEachTile(tile => {
      if (tile.index === 329) {
        hide = this.hideGroup.create(
          tile.getCenterX(),
          tile.getCenterY(),
          "hide"
        );
        this.groundLayer.removeTileAt(tile.x, tile.y);
      }
    });

    // water 타일 변경
    this.groundLayer.forEachTile(tile => {
      if (tile.index === 1295) {
        this.water = this.spikeGroup.create(
          tile.getCenterX(),
          tile.getCenterY(),
          "water"
        );
        this.groundLayer.removeTileAt(tile.x, tile.y);
      }
    });

    // magma 타일 변경
    this.groundLayer.forEachTile(tile => {
      if (tile.index === 1284) {
        this.magma = this.spikeGroup.create(
          tile.getCenterX(),
          tile.getCenterY(),
          "magma"
        );
        this.groundLayer.removeTileAt(tile.x, tile.y);
      }
    });

    // 동그라미 충돌 추가
    this.physics.world.addCollider(this.player.sprite, this.hideGroup);

    // 스프링 타일 교체
    this.springGroup = this.physics.add.staticGroup();
    this.groundLayer.forEachTile(tile => {
      if (tile.index === 1388) {
        const spring = this.springGroup.create(
          tile.getCenterX(),
          tile.getCenterY(),
          "spring"
        );
        this.groundLayer.removeTileAt(tile.x, tile.y);
      }
    });

    // 카메라 앵글을 맵크기 내에서 움직이도록 제한
    this.cameras.main.setBounds(0, 0, map.widthInPixels, map.heightInPixels);

    // 카메라가 플레이어를 따라 이동
    this.cameras.main.startFollow(this.player.sprite);

    // 플레이 시간을 텍스트로 띄우기
    timeText = this.add
      .text(16, 16, "time", {
        font: "18px monospace",
        fill: "#f20505",
        padding: { x: 20, y: 10 },
        borderColor: "#f20505"
        //backgroundColor: "#ffffff"
      })
      // 텍스트 창이 시야[카메라]를 따라 함께 고정되어 움직이도록 함
      .setScrollFactor(0);

    // 마커 생성
    this.marker = new Marker(this, map);

    // 상재 갯수 텍스트로 표기
    this.boxText = this.add
      .text(650, 20, "box: " + boxList[a] + "\n" + "wood: " + woodNum, {
        font: "20px monospace",
        fill: "#ffff2f",
        borderColor: "#ffff2f"
        //backgroundColor: "#ffffff"
      })
      .setScrollFactor(0);

    this.input.on("pointerdown", () => {
      // 상자 클릭 한 번 당 개수 -1 차감되게 하기
      box -= 1;
      nowPoint = this.input.activePointer.positionToCamera(this.cameras.main);

      // 상자 개수 0되면 marker표시 사라지게 하기
      if (box < 1) {
        box = 0;
        this.marker.destroy();
      }
      this.boxText.setText("box: " + box + "\n" + "wood: " + woodNum);
    });
  }

  update(time, delta) {
    //플레이시간을 서버로 보내기 위한 ajax통신 시도중
    this.saveTime();

    // return 하지 않을 경우, 다시 시작 되지 않음
    if (this.isPlayerDead || this.isNext) {
      // 죽거나, 스테이지 넘어갈 경우,
      // 배경음악 멈춤 - 멈추지 않으면 음악 겹쳐서 돌림노래처럼 여러 개 동시에 재생됨
      this.music.pause();

      return;
    }

    this.player.update();
    this.marker.update();

    // robot1 객체가 있다면
    if (this.robot1_start !== null) {
      // 방향전환~
      if (this.robot1.robot.x >= this.robot1_end.x) {
        this.robot1.update(true);
      }
      if (this.robot1.robot.x <= this.robot1_start.x) {
        this.robot1.update(false);
      }
    }

    if (this.robot2_start !== null) {
      if (this.robot2.robot.x >= this.robot2_end.x) {
        this.robot2.update(true);
      }
      if (this.robot2.robot.x <= this.robot2_start.x) {
        this.robot2.update(false);
      }
    }

    if (this.robot3_start !== null) {
      if (this.robot3.robot.x >= this.robot3_end.x) {
        this.robot3.update(true);
      }
      if (this.robot3.robot.x <= this.robot3_start.x) {
        this.robot3.update(false);
      }
    }

    if (this.dog1_start !== null) {
      if (this.dog1.dog.x >= this.dog1_end.x) {
        this.dog1.update(true);
      }
      if (this.dog1.dog.x <= this.dog1_start.x) {
        this.dog1.update(false);
      }
    }

    if (this.dog2_start !== null) {
      if (this.dog2.dog.x >= this.dog2_end.x) {
        this.dog2.update(true);
      }
      if (this.dog2.dog.x <= this.dog2_start.x) {
        this.dog2.update(false);
      }
    }

    if (this.dog3_start !== null) {
      if (this.dog3.dog.x >= this.dog3_end.x) {
        this.dog3.update(true);
      }
      if (this.dog3.dog.x <= this.dog3_start.x) {
        this.dog3.update(false);
      }
    }

    if (this.mazayong1_start !== null) {
      if (this.mazayong1.mazayong.x >= this.mazayong1_end.x) {
        this.mazayong1.update(true);
      }
      if (this.mazayong1.mazayong.x <= this.mazayong1_start.x) {
        this.mazayong1.update(false);
      }
    }

    if (this.mazayong2_start !== null) {
      if (this.mazayong2.mazayong.x >= this.mazayong2_end.x) {
        this.mazayong2.update(true);
      }
      if (this.mazayong2.mazayong.x <= this.mazayong2_start.x) {
        this.mazayong2.update(false);
      }
    }

    if (this.mazayong3_start !== null) {
      if (this.mazayong3.mazayong.x >= this.mazayong3_end.x) {
        this.mazayong3.update(true);
      }
      if (this.mazayong3.mazayong.x <= this.mazayong3_start.x) {
        this.mazayong3.update(false);
      }
    }

    if (this.slime1_start !== null) {
      if (this.slime1.slime.x >= this.slime1_end.x) {
        this.slime1.update(true);
      }
      if (this.slime1.slime.x <= this.slime1_start.x) {
        this.slime1.update(false);
      }
    }

    if (this.slime2_start !== null) {
      if (this.slime2.slime.x >= this.slime2_end.x) {
        this.slime2.update(true);
      }
      if (this.slime2.slime.x <= this.slime2_start.x) {
        this.slime2.update(false);
      }
    }

    if (this.slime3_start !== null) {
      if (this.slime3.slime.x >= this.slime3_end.x) {
        this.slime3.update(true);
      }
      if (this.slime3.slime.x <= this.slime3_start.x) {
        this.slime3.update(false);
      }
    }

    // 총 플레이 시간 표기
    setInterval(total, 1000);
    function total() {
      totalTime += 0.001;
    }

    // 플레이 시간 실시간 업데이트
    timeText.setText("Time : " + totalTime.toFixed(3) + " sec");

    if (box > 0) {
      // 1.동적으로 상자만들기
      worldPoint = this.input.activePointer.positionToCamera(this.cameras.main);

      // 2.동적으로 상자만들기
      if (this.input.manager.activePointer.isDown) {
        this.groundLayer.putTileAtWorldXY(353, worldPoint.x, worldPoint.y);
      }

      // 동적으로 생성된 타일에 충돌이벤트 걸기
      pointer = this.input.activePointer;

      worldPoint = pointer.positionToCamera(this.cameras.main);
      if (pointer.isDown) {
        //포인트가 눌렸을 떄

        const tile = this.groundLayer.putTileAtWorldXY(
          // 눌린 시점[새타일 생성 위치]의 좌표를 변수에 담아 =>
          // 타일맵에서 사용한 이미지의 번호[동적 타일의 이미지를 가지고 있는 이미지로 변경]
          1409,
          worldPoint.x,
          worldPoint.y
        );

        // => 새롭게 타일이 생성 된, 해당 위치에 충돌이벤트를 걸어줌
        tile.setCollision(true);
      }
    }

    this.camReset = function() {
      const cam = this.cameras.main;
      cam.shake(100, 0.05);
      cam.fade(250, 0, 0, 0);
      this.player.freeze();
      this.marker.destroy();
      cam.once("camerafadeoutcomplete", () => {
        this.player.destroy();
        this.scene.restart();
      });
    };

    this.deadStart = function() {
      // player 죽었는지 여부 판단 위한 변수
      this.isPlayerDead = true;
      // 박스 개수 초기화
      box = boxList[a];
      // 장작 개수 초기화
      woodNum = 0;
      this.camReset();
    };

    if (
      // robot 객체 존재 & 플레이어와 오버랩일시,
      this.robot1_start !== null &&
      this.physics.world.overlap(this.player.sprite, this.robot1.robot)
    ) {
      // 1. 플레이어가 밟으면 --> 적 사망
      if (this.player.sprite.y < this.robot1.robot.y) {
        this.player.bounce();
        this.robot1.robot.destroy();
        return;
      }
      // 2. 그 외 경우 플레이어 사망
      this.deadStart();
    }

    if (
      this.robot2_start !== null &&
      this.physics.world.overlap(this.player.sprite, this.robot2.robot)
    ) {
      if (this.player.sprite.y < this.robot2.robot.y) {
        this.player.bounce();
        this.robot2.robot.destroy();
        return;
      }
      this.deadStart();
    }

    if (
      this.robot3_start !== null &&
      this.physics.world.overlap(this.player.sprite, this.robot3.robot)
    ) {
      if (this.player.sprite.y < this.robot3.robot.y) {
        this.player.bounce();
        this.robot3.robot.destroy();
        return;
      }
      this.deadStart();
    }

    if (
      this.dog1_start !== null &&
      this.physics.world.overlap(this.player.sprite, this.dog1.dog)
    ) {
      if (this.player.sprite.y < this.dog1.dog.y) {
        this.player.bounce();
        this.dog1.dog.destroy();
        return;
      }
      this.deadStart();
    }

    if (
      this.dog2_start !== null &&
      this.physics.world.overlap(this.player.sprite, this.dog2.dog)
    ) {
      if (this.player.sprite.y < this.dog2.dog.y) {
        this.player.bounce();
        this.dog2.dog.destroy();
        return;
      }
      this.deadStart();
    }

    if (
      this.dog3_start !== null &&
      this.physics.world.overlap(this.player.sprite, this.dog3.dog)
    ) {
      if (this.player.sprite.y < this.dog3.dog.y) {
        this.player.bounce();
        this.dog3.dog.destroy();
        return;
      }
      this.deadStart();
    }

    if (
      this.mazayong1_start !== null &&
      this.physics.world.overlap(this.player.sprite, this.mazayong1.mazayong)
    ) {
      if (this.player.sprite.y < this.mazayong1.mazayong.y) {
        this.player.bounce();
        this.mazayong1.mazayong.destroy();
        return;
      }
      this.deadStart();
    }

    if (
      this.mazayong2_start !== null &&
      this.physics.world.overlap(this.player.sprite, this.mazayong2.mazayong)
    ) {
      if (this.player.sprite.y < this.mazayong2.mazayong.y) {
        this.player.bounce();
        this.mazayong2.mazayong.destroy();
        return;
      }
      this.deadStart();
    }

    if (
      this.mazayong3_start !== null &&
      this.physics.world.overlap(this.player.sprite, this.mazayong3.mazayong)
    ) {
      if (this.player.sprite.y < this.mazayong3.mazayong.y) {
        this.player.bounce();
        this.mazayong3.mazayong.destroy();
        return;
      }
      this.deadStart();
    }

    if (
      this.slime1_start !== null &&
      this.physics.world.overlap(this.player.sprite, this.slime1.slime)
    ) {
      if (this.player.sprite.y < this.slime1.slime.y) {
        this.player.bounce();
        this.slime1.slime.destroy();
        return;
      }
      this.deadStart();
    }

    if (
      this.slime2_start !== null &&
      this.physics.world.overlap(this.player.sprite, this.slime2.slime)
    ) {
      if (this.player.sprite.y < this.slime2.slime.y) {
        this.player.bounce();
        this.slime2.slime.destroy();
        return;
      }
      this.deadStart();
    }

    if (
      this.slime3_start !== null &&
      this.physics.world.overlap(this.player.sprite, this.slime3.slime)
    ) {
      if (this.player.sprite.y < this.slime3.slime.y) {
        this.player.bounce();
        this.slime3.slime.destroy();
        return;
      }
      this.deadStart();
    }

    // 장작과 플레이어 오버랩 되었을 때, 필요 개수 -1,
    if (
      this.w1 !== null &&
      this.physics.world.overlap(this.player.sprite, this.wood1.wood)
    ) {
      woodNum -= 1;
      this.boxText.setText("box: " + box + "\n" + "wood: " + woodNum);
      this.wood1.destroy();
    }
    if (
      this.w2 !== null &&
      this.physics.world.overlap(this.player.sprite, this.wood2.wood)
    ) {
      woodNum -= 1;
      this.boxText.setText("box: " + box + "\n" + "wood: " + woodNum);
      this.wood2.destroy();
    }
    if (
      this.w3 !== null &&
      this.physics.world.overlap(this.player.sprite, this.wood3.wood)
    ) {
      woodNum -= 1;
      this.boxText.setText("box: " + box + "\n" + "wood: " + woodNum);
      this.wood3.destroy();
    }
    if (
      this.w4 !== null &&
      this.physics.world.overlap(this.player.sprite, this.wood4.wood)
    ) {
      woodNum -= 1;
      this.boxText.setText("box: " + box + "\n" + "wood: " + woodNum);
      this.wood4.destroy();
    }
    if (
      this.w5 !== null &&
      this.physics.world.overlap(this.player.sprite, this.wood5.wood)
    ) {
      woodNum -= 1;
      this.boxText.setText("box: " + box + "\n" + "wood: " + woodNum);
      this.wood5.destroy();
    }
    if (
      this.w6 !== null &&
      this.physics.world.overlap(this.player.sprite, this.wood6.wood)
    ) {
      woodNum -= 1;
      this.boxText.setText("box: " + box + "\n" + "wood: " + woodNum);
      this.wood6.destroy();
    }
    if (
      this.w7 !== null &&
      this.physics.world.overlap(this.player.sprite, this.wood7.wood)
    ) {
      woodNum -= 1;
      this.boxText.setText("box: " + box + "\n" + "wood: " + woodNum);
      this.wood7.destroy();
    }
    if (
      this.w8 !== null &&
      this.physics.world.overlap(this.player.sprite, this.wood8.wood)
    ) {
      woodNum -= 1;
      this.boxText.setText("box: " + box + "\n" + "wood: " + woodNum);
      this.wood8.destroy();
    }
    if (
      this.w9 !== null &&
      this.physics.world.overlap(this.player.sprite, this.wood9.wood)
    ) {
      woodNum -= 1;
      this.boxText.setText("box: " + box + "\n" + "wood: " + woodNum);
      this.wood9.destroy();
    }
    if (
      this.w10 !== null &&
      this.physics.world.overlap(this.player.sprite, this.wood10.wood)
    ) {
      woodNum -= 1;
      this.boxText.setText("box: " + box + "\n" + "wood: " + woodNum);
      this.wood10.destroy();
    }
    if (
      this.player.sprite.y > this.groundLayer.height ||
      this.physics.world.overlap(this.player.sprite, this.spikeGroup)
    ) {
      this.deadStart();
    }

    // 스프링 점프 이벤트
    if (this.physics.world.overlap(this.player.sprite, this.springGroup)) {
      this.player.bounce();
    }

    // 장작 다 먹어야 다음스테이지로 이동가능
    if (woodNum === 0) {
      // 다음 스테이지로 전환

      this.next.exit.setVisible(true);

      if (this.physics.world.overlap(this.player.sprite, this.next.exit)) {
        this.isNext = true;
        // 상자값 초기화
        box = boxList[a];
        // 다음 scene을 위한 설정
        a += 1;
        //alert(a);

        // 다음 스테이지 => 장작 개수 초기화
        woodNum = 0;

        this.robot1 = null;
        this.dog1 = null;
        this.mazayong1 = null;
        this.slime1 = null;

        this.scene.start(this.map, this.data);
        this.camReset();
      }
    }
  }
}
