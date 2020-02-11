import Phaser from "phaser";

// var nameList = ["robot", "dog", "mazayong", "slime"];
// var startIdxList = [91, 78, 33, 104];
// var endIdxList = [94, 83, 37, 110];
// var frameRateList = [4, 6, 5, 7];

export default class Dog {
  constructor(scene, x, y) {
    this.scene = scene;
    const anims = scene.anims;

    anims.create({
      key: "dog-move",
      frames: anims.generateFrameNumbers("enemy", {
        start: 78,
        end: 83
      }),
      frameRate: 6,
      repeat: -1
    });

    this.dog = scene.physics.add
      .sprite(x, y, "enemy", 83) // 생성시 index가 78인 이미지 출력
      .setVelocityX(60) // 오른쪽방향으로 30씩 이동
      .anims.play("dog-move", true) // dog-move 애니메이션 효과 플레이
      .setSize(18, 24) // 자연스럽게 사이즈 조정
      .setOffset(7, 9); // 조정된 사이즈에 맞게 재배치
  }

  freeze() {
    this.dog.body.moves = false;
  }

  update(f) {
    // const dog = this;
    switch (f) {
      case true:
        this.dog.setVelocityX(-60);
        this.dog.setFlipX(true);
        break;
      case false:
        this.dog.setVelocityX(60);
        this.dog.setFlipX(false);
        break;
      default:
        break;
    }
  }

  destroy() {
    this.dog.destroy();
  }
}
