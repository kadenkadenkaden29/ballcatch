import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameController {
    private Pane gamePane;
    private Rectangle basket;
    private List<FallingObject> fallingObjects;
    private Random random;
    private AnimationTimer gameLoop;
    private int score;
    private int lives;
    private Label scoreLabel;
    private Label livesLabel;

    public GameController() {
        gamePane = new Pane();
        basket = new Rectangle(50, 20, Color.BLUE);
        basket.setY(550);
        gamePane.getChildren().add(basket);

        fallingObjects = new ArrayList<>();
        random = new Random();

        score = 0;
        lives = 10;

        scoreLabel = new Label("Score: 0");
        livesLabel = new Label("Lives: 10");
        scoreLabel.setLayoutX(10);
        scoreLabel.setLayoutY(10);
        livesLabel.setLayoutX(10);
        livesLabel.setLayoutY(30);

        gamePane.getChildren().addAll(scoreLabel, livesLabel);

        gamePane.setOnMouseMoved(event -> basket.setX(event.getX() - basket.getWidth() / 2));
    }

    public Pane getGamePane() {
        return gamePane;
    }

    public void startGame() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (random.nextInt(100) < 2) {
                    FallingObject obj = new FallingObject(random.nextInt(400), 0);
                    fallingObjects.add(obj);
                    gamePane.getChildren().add(obj.getShape());
                }

                for (FallingObject obj : fallingObjects) {
                    obj.update();
                    if (obj.getShape().getBoundsInParent().intersects(basket.getBoundsInParent())) {
                        gamePane.getChildren().remove(obj.getShape());
                        obj.setCaught(true);
                        score++;
                        scoreLabel.setText("Score: " + score);
                    } else if (obj.getShape().getCenterY() > 600) {
                        gamePane.getChildren().remove(obj.getShape());
                        obj.setCaught(true);
                        lives--;
                        livesLabel.setText("Lives: " + lives);
                    }
                }

                fallingObjects.removeIf(FallingObject::isCaught);

                if (score >= 30) {
                    gameLoop.stop();
                    scoreLabel.setText("You win! Final Score: " + score);
                } else if (lives <= 0) {
                    gameLoop.stop();
                    livesLabel.setText("Game Over! Final Score: " + score);
                }
            }
        };
        gameLoop.start();
    }
}