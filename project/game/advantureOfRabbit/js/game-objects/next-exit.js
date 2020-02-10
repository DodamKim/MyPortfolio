import Phaser from "phaser";

// 캠프파이어 객체 생성
export default class Exit {
  constructor(scene, x, y) {
    this.scene = scene;

    const anims = scene.anims;
    anims.create({
      key: "exit-move",
      frames: anims.generateFrameNumbers("fire", { start: 0, end: 9 }),
      frameRate: 5,
      repeat: -1
    });
    //console.log("Exit anims 생성");

    this.exit = scene.physics.add
      .sprite(x, y, "fire", 0)
      .anims.play("exit-move", true)
      .setSize(18, 24)
      .setOffset(7, 9);
    //console.log("Exit physics 생성");
  }

  freeze() {
    this.exit.body.moves = false;
  }

  update(start, end) {
    const exit = this;
  }

  destroy() {
    this.exit.destroy();
  }
}
