import Phaser from "phaser";

export default class Arrow {
  constructor(scene, x, y, name) {
    this.scene = scene;
    const anims = scene.anims;

    anims.create({
      key: "arrow-down",
      frames: anims.generateFrameNumbers("arrow", {
        start: 0,
        end: 2
      }),
      frameRate: 6,
      repeat: -1
    });

    anims.create({
      key: "arrow-left",
      frames: anims.generateFrameNumbers("arrow", {
        start: 3,
        end: 5
      }),
      frameRate: 6,
      repeat: -1
    });

    anims.create({
      key: "arrow-right",
      frames: anims.generateFrameNumbers("arrow", {
        start: 6,
        end: 8
      }),
      frameRate: 6,
      repeat: -1
    });

    anims.create({
      key: "arrow-up",
      frames: anims.generateFrameNumbers("arrow", {
        start: 9,
        end: 11
      }),
      frameRate: 6,
      repeat: -1
    });

    switch (name) {
      case "left":
        this.left = scene.physics.add
          .sprite(x, y, "arrow", 0)
          .setGravity(0, -1000)
          .anims.play("arrow-left", true);
        break;

      case "right":
        this.right = scene.physics.add
          .sprite(x, y, "arrow", 0)
          .setGravity(0, -1000)
          .anims.play("arrow-right", true);
        break;

      case "up":
        this.up = scene.physics.add
          .sprite(x, y, "arrow", 0)
          .setGravity(0, -1000)
          .anims.play("arrow-up", true);
        break;

      case "down":
        this.down = scene.physics.add
          .sprite(x, y, "arrow", 0) // 생성시 index가 78인 이미지 출력
          .setGravity(0, -1000) // 중력 제거
          .anims.play("arrow-down", true); // 'key'의 애니메이션 효과 플레이
        break;

      default:
        break;
    }
  }

  update() {}

  destroy() {
    this.arrow.destroy();
  }
}
