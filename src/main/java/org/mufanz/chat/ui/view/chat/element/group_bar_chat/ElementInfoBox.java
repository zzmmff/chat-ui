package org.mufanz.chat.ui.view.chat.element.group_bar_chat;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
import org.mufanz.chat.ui.util.AutoSizeTool;
import org.mufanz.chat.ui.util.DateUtil;

import java.io.File;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.Date;

/**
 *
 */
public class ElementInfoBox {

    private Pane pane;

    private Pane head;              // 头像
    private Label nikeName;         // 昵称区
    private Label infoContentArrow; // 内容箭头
    private TextArea infoContent;   // 内容


    /**
     * 好友消息
     *
     * @param userNickName
     * @param userHead
     * @param msg
     * @param msgType
     * @return
     */
    public Pane left(String userNickName, String userHead, String msg, Integer msgType) {

        double autoHeight = 0;
        double autoWidth = 0;
        pane = new Pane();

        if (msgType !=2){
            autoHeight = AutoSizeTool.getHeight(msg);
            autoWidth = AutoSizeTool.getWidth(msg);
            pane.setPrefSize(500, 50 + autoHeight);
        }else {
            pane.setPrefSize(500, 150);
        }

        pane.getStyleClass().add("infoBoxElement");
        ObservableList<Node> children = pane.getChildren();

        // 头像
        head = new Pane();
        head.setPrefSize(50, 50);
        head.setLayoutX(15);
        head.setLayoutY(15);
        head.getStyleClass().add("box_head");
        head.setStyle(String.format("-fx-background-image: url('/fxml/chat/img/head/%s.png')", userHead));
        children.add(head);

        // 昵称
        nikeName = new Label();
        nikeName.setPrefSize(450, 20);
        nikeName.setLayoutX(75);
        nikeName.setLayoutY(5);
        nikeName.setText(userNickName); // "小傅哥 | bugstack.cn"
        nikeName.getStyleClass().add("box_nikeName");
        children.add(nikeName);

        // 箭头
        infoContentArrow = new Label();
        infoContentArrow.setPrefSize(5, 20);
        infoContentArrow.setLayoutX(75);
        infoContentArrow.setLayoutY(30);
        infoContentArrow.getStyleClass().add("box_infoContent_arrow");
        children.add(infoContentArrow);

        switch (msgType) {
            case 0:
                // 内容
                infoContent = new TextArea();
                infoContent.setPrefWidth(autoWidth);
                infoContent.setPrefHeight(autoHeight);
                infoContent.setLayoutX(80);
                infoContent.setLayoutY(30);
                infoContent.setWrapText(true);
                infoContent.setEditable(false);
                infoContent.setText((String) msg);
                infoContent.getStyleClass().add("box_infoContent_left");
                children.add(infoContent);
                break;
            case 1:
                Label infoContentFace = new Label();
                infoContentFace.setPrefWidth(60);
                infoContentFace.setPrefHeight(40);
                infoContentFace.setLayoutX(80);
                infoContentFace.setLayoutY(30);
                infoContentFace.setStyle(String.format("-fx-background-image: url('/fxml/face/img/%s.png');-fx-background-position: center center;-fx-background-repeat: no-repeat;-fx-background-color: #ffffff;-fx-border-width: 0 1px 1px 0;-fx-border-radius: 2px;-fx-background-radius: 2px;", msg));
                children.add(infoContentFace);
                break;
            case 2:
                File file = new File(msg);
                Button infoContentFile = new Button();
                infoContentFile.setPrefHeight(100);
                infoContentFile.setPrefWidth(250);
                infoContentFile.setLayoutX(80);
                infoContentFile.setLayoutY(30);
                infoContentFile.getStyleClass().add("box_infoContent_file_right");

                String name = file.getName();
                //显示文件名
                Label fileNameLabel = new Label();
                fileNameLabel.setPrefWidth(135);
                fileNameLabel.setPrefHeight(50);
                if (name.length() > 18)
                    fileNameLabel.setText(name.substring(0, 18) + "...");
                else fileNameLabel.setText(name);
                fileNameLabel.getStyleClass().add("box_infoContent_file_name");
                fileNameLabel.setLayoutX(90);
                fileNameLabel.setLayoutY(45);
                fileNameLabel.setWrapText(true);

                //显示文件大小
                Label label = new Label();
                label.setText(getSize(file.length()));
                label.setLayoutX(90);
                label.setLayoutY(95);
                label.setTextFill(Color.web("#999999"));

                children.add(infoContentFile);
                children.add(label);
                children.add(fileNameLabel);
                break;
        }

        return pane;
    }

    /**
     * 个人消息
     *
     * @param userNickName 用户昵称
     * @param userHead     用户头像
     * @param msg          消息
     * @param msgType      类型；0文字消息、1固定表情
     * @return
     */
    public Pane right(String userNickName, String userHead, String msg, Integer msgType) {

        double autoHeight = 0;
        double autoWidth = 0;
        pane = new Pane();

        if (msgType != 2) {
            autoHeight = AutoSizeTool.getHeight(msg);
            autoWidth = AutoSizeTool.getWidth(msg);
            pane.setPrefSize(500, 50 + autoHeight);
        } else {
            pane.setPrefSize(500, 150);
        }
        pane.getStyleClass().add("infoBoxElement");
        pane.setLayoutX(853);
        pane.setLayoutY(0);
        ObservableList<Node> children = pane.getChildren();

        // 头像
        head = new Pane();
        head.setPrefSize(50, 50);
        head.setLayoutX(770);
        head.setLayoutY(15);
        head.getStyleClass().add("box_head");
        head.setStyle(String.format("-fx-background-image: url('/fxml/chat/img/head/%s.png')", userHead));
        children.add(head);

        // 箭头
        infoContentArrow = new Label();
        infoContentArrow.setPrefSize(5, 20);
        infoContentArrow.setLayoutX(755);
        infoContentArrow.setLayoutY(15);
        infoContentArrow.getStyleClass().add("box_infoContent_arrow");
        children.add(infoContentArrow);

        switch (msgType) {
            case 0:
                // 内容：文字
                infoContent = new TextArea();
                infoContent.setPrefWidth(autoWidth);
                infoContent.setPrefHeight(autoHeight);
                infoContent.setLayoutX(755 - autoWidth);
                infoContent.setLayoutY(15);
                infoContent.setWrapText(true);
                infoContent.setEditable(false);
                infoContent.setText((String) msg);
                infoContent.getStyleClass().add("box_infoContent_right");
                children.add(infoContent);
                break;
            case 1:
                Label infoContentFace = new Label();
                infoContentFace.setPrefWidth(60);
                infoContentFace.setPrefHeight(40);
                infoContentFace.setLayoutX(755 - 60);
                infoContentFace.setLayoutY(15);
                infoContentFace.setStyle(String.format("-fx-background-image: url('/fxml/face/img/%s.png');-fx-background-position: center center;-fx-background-repeat: no-repeat;-fx-background-color: #9eea6a;-fx-border-width: 0 1px 1px 0;-fx-border-radius: 2px;-fx-background-radius: 2px;", msg));
                children.add(infoContentFace);
                break;
            case 2: //文件
                File file = new File(msg);
                Button infoContentFile = new Button();
                infoContentFile.setPrefHeight(100);
                infoContentFile.setPrefWidth(250);
                infoContentFile.setLayoutX(755 - 250);
                infoContentFile.setLayoutY(15);
                infoContentFile.getStyleClass().add("box_infoContent_file_right");
                String name = file.getName();

                //显示文件名
                Label fileNameLabel = new Label();
                fileNameLabel.setPrefWidth(135);
                fileNameLabel.setPrefHeight(50);
                if (name.length() > 18)
                    fileNameLabel.setText(name.substring(0, 18) + "...");
                else fileNameLabel.setText(name);
                fileNameLabel.getStyleClass().add("box_infoContent_file_name");
                fileNameLabel.setLayoutX(755 - 240);
                fileNameLabel.setLayoutY(30);
                fileNameLabel.setWrapText(true);

                //显示文件大小
                Label label = new Label();
                label.setText(getSize(file.length()));
                label.setLayoutX(755 - 240);
                label.setLayoutY(80);
                label.setTextFill(Color.web("#999999"));

                children.add(infoContentFile);
                children.add(label);
                children.add(fileNameLabel);
        }

        return pane;
    }

    private String getSize(long byteNum) {

        if (byteNum < (1 << 10)) {
            return (byteNum + "B");
        }

        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);

        if (byteNum < (1 << 20)) {

            return (df.format((double) byteNum / (1 << 10)) + "KB");
        }

        if (byteNum < (1 << 30)) {
            return (df.format((double) byteNum / (1 << 20)) + "MB");
        }

        return (df.format((double) byteNum / (1 << 30)) + "GB");
    }
}


