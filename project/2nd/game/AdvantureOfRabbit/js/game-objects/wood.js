import Phaser from "phaser";

// 나무 객제 생성
export default class Wood {
  constructor(scene, x, y) {
    this.scene = scene;
    const anims = scene.anims;

    anims.create({
      key: "wood-move",
      frames: anims.generateFrameNumbers("wood", {
        start: 0,
        end: 2
      }),
      frameRate: 6,
      repeat: -1
    });

    this.wood = scene.physics.add
      .sprite(x, y, "wood", 0) // 생성시 index가 78인 이미지 출력
      .setGravity(0, -1000) // 중력 제거
      .anims.play("wood-move", true); // dog-move 애니메이션 효과 플레이
  }

  freeze() {
    this.wood.body.moves = false;
  }

  update() {}

  destroy() {
    this.wood.destroy();
  }
}
