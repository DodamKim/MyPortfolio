import Phaser from "phaser";
import PlatformerScene from "../js/scenes/platformer-scene.js";

const config = {
  type: Phaser.AUTO,
  width: 800,
  height: 600,
  backgroundColor: "#1d212d",
  parent: "game-container",
  pixelArt: true,
  scene: PlatformerScene,
  // scene: SimpleScene,
  physics: {
    default: "arcade",
    arcade: {
      gravity: { y: 1000 }
    }
  }
};

const game = new Phaser.Game(config);
