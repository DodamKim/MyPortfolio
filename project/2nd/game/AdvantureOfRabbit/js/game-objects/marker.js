// 마우스 포인터 빨간 상자 만들기& 마우스 따라 움직이게 하기
export default class MouseTileMarker {
  constructor(scene, map) {
    this.map = map;
    this.scene = scene;

    this.graphics = scene.add.graphics();
    this.graphics.lineStyle(5, 0xffffff, 1);
    this.graphics.strokeRect(0, 0, map.tileWidth, map.tileHeight);
    this.graphics.lineStyle(3, 0xff4f78, 1);
    this.graphics.strokeRect(0, 0, map.tileWidth, map.tileHeight);
  }

  update() {
    const pointer = this.scene.input.activePointer;
    const worldPoint = pointer.positionToCamera(this.scene.cameras.main);
    const pointerTileXY = this.map.worldToTileXY(worldPoint.x, worldPoint.y);
    const snappedWorldPoint = this.map.tileToWorldXY(
      pointerTileXY.x,
      pointerTileXY.y
    );
    this.graphics.setPosition(snappedWorldPoint.x, snappedWorldPoint.y);
  }

  hide() {}

  destroy() {
    this.graphics.destroy();
  }
}
