import Phaser from "phaser";

// var nameList = ["robot", "dog", "mazayong", "slime"];
// var startIdxList = [91, 78, 33, 104];
// var endIdxList = [94, 83, 37, 110];
// var frameRateList = [4, 6, 5, 7];

export default class Robot {
  constructor(scene, x, y) {
    this.scene = scene;
    const anims = scene.anims;

    anims.create({
      key: "robot-move",
      frames: anims.generateFrameNumbers("enemy", {
        start: 91,
        end: 94
      }),
      frameRate: 4,
      repeat: -1
    });

    this.robot = scene.physics.add
      .sprite(x, y, "enemy", 91) // 생성시 index가 78인 이미지 출력
      .setGravity(0, -1000) // 중력 제거
      .anims.play("robot-move", true) // dog-move 애니메이션 효과 플레이
      .setSize(18, 24) // 자연스럽게 사이즈 조정
      .setOffset(7, 9); // 조정된 사이즈에 맞게 재배치
  }

  freeze() {
    this.robot.body.moves = false;
  }

  update(f) {
    // const robot = this;
    switch (f) {
      case true:
        this.robot.setVelocityX(-100);
        this.robot.setFlipX(true);
        break;
      case false:
        this.robot.setVelocityX(100);
        this.robot.setFlipX(false);
        break;
      default:
        break;
    }
  }

  destroy() {
    this.robot.destroy();
  }
}
