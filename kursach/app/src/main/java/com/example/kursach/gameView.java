package com.example.kursach;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.ArrayList;

public class gameView extends SurfaceView implements Runnable {
    public static int maxX = 20; // размер по горизонтали
    public static int maxY = 28; // размер по вертикали
    public static float unitW = 0; // пикселей в юните по горизонтали
    public static float unitH = 0; // пикселей в юните по вертикали
    private boolean firstTime = true;
    public boolean gameRunning = true;
    private Covid covid;
    public Thread gameThread = null;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private Human human;
    private int jump = 0;
    private boolean col;
    MainActivity main = new MainActivity();


    public gameView(Context context) {
        super(context);
        //инициализируем обьекты для рисования
        surfaceHolder = getHolder();
        paint = new Paint();
        // инициализируем поток
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {
        while (gameRunning) {
            update();
            draw();
            checkCollision();
            checkIfNewCell();
            control();
        }

    }

    private void update() {
        if (!firstTime) {
            covid.update(); // обновление положения вируса
            if (jump>=20) {
                col = false;
                jump = 0;
            }
            if (col) {
                covid.y -= 0.2;
                jump++;
            } else
                covid.y += 0.06;
            if (human.isCollision(covid.x, covid.y, covid.size) || covid.y < 0 || covid.y > 25) { // проверка на окончание игры
                gameRunning = false;
                gameThread.interrupt();
                main.onPause();
            }
        }
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {  //проверяем валидный ли surface

            if (firstTime) { // инициализация при первом запуске
                firstTime = false;
                unitW = surfaceHolder.getSurfaceFrame().width() / maxX; // вычисляем число пикселей в юните
                unitH = surfaceHolder.getSurfaceFrame().height() / maxY;
                covid = new Covid(getContext()); // добавляем корабль
                human = new Human(getContext());
            }
            canvas = surfaceHolder.lockCanvas(); // закрываем canvas
            canvas.drawColor(Color.parseColor("#FFA240"));

            covid.drow(paint, canvas); // рисуем корабль
            human.drow(paint, canvas);
            for (Cell cell : cells) { // рисуем клетки
                cell.drow(paint, canvas);
            }
            surfaceHolder.unlockCanvasAndPost(canvas); // открываем canvas
        }
    }

    private void control() { // пауза на 17 миллисекунд
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    private ArrayList<Cell> cells = new ArrayList<>(); // тут будут харанится астероиды
    private final int CELLS_INTERVAL = 25; // время через которое появляются астероиды (в итерациях)
    private int currentTime = 0;
    public void checkCollision() { // перебираем все клетки и проверяем не касается ли одна из них вируса
        for (Cell cell : cells) {
            if (cell.isCollision(covid.x, covid.y, covid.size)) {
                col = true;
            }
        }
    }

    private void checkIfNewCell() { // каждые 25 итераций добавляем либо обновляем клетку
        if (currentTime >= CELLS_INTERVAL) {
            Cell cell = new Cell(getContext());
            cells.add(cell);
            currentTime = 0;
        } else {
            currentTime++;
        }
        if (cells.size() == 4) {
            cells.remove(0);
        }
    }


}


