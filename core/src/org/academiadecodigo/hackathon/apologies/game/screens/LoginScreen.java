package org.academiadecodigo.hackathon.apologies.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mail.vandrake.VLib;
import com.mail.vandrake.control.VUtils;
import com.mail.vandrake.scene2d.VImage;
import com.mail.vandrake.scene2d.VScreen;
import com.mail.vandrake.scene2d.VTextField;
import org.academiadecodigo.hackathon.apologies.AllApologies;
import org.academiadecodigo.hackathon.apologies.ButtonFactory;
import org.academiadecodigo.hackathon.apologies.Constants;

/**
 * Created by codecadet on 23/11/17.
 */
public class LoginScreen extends VScreen {

    BitmapFont guiFont;
    private Image logoImage;

    @Override
    public void show() {

        super.show();

        guiFont = new BitmapFont();
        //guiFont = VFont.generateFont(Constants.DEFAULT_FONT, 16);

        AllApologies.inputMultiplexer.addProcessor(getGuiStage());

        setupLogoImage();

        setupQuitButton();

        setupUserButtons(Constants.BUTTON_REGISTER);

        setupUserButtons(Constants.BUTTON_LOGIN);

        setupUsernameTextField();
    }

    private void setupUsernameTextField() {

        TextField usernameTextField = addTextField(Constants.TEXT_FIELD_USERNAME, guiFont);
        VUtils.centerX(usernameTextField);
        usernameTextField.setY(logoImage.getY() - usernameTextField.getHeight() - 16);

        TextField passwordTextField = addTextField(Constants.TEXT_FIELD_PASSWORD, guiFont);
        passwordTextField.setPosition(usernameTextField.getX(), usernameTextField.getY() - passwordTextField.getHeight() - 16);
        //TODO Fix the password mode
        passwordTextField.setPasswordMode(true);
    }

    private TextField addTextField(String defaultText, BitmapFont font) {

        TextField textField = VTextField.txtField(VLib.guiSkin, "", defaultText, font, Color.WHITE);
        textField.setPasswordMode(true);
        getGuiStage().addActor(textField);
        return textField;
    }

    private void setupUserButtons(String text) {

        int space = 4;
        TextButton userButton = addButton(text, 0, 60);
        final boolean isRegister = text.equals(Constants.BUTTON_REGISTER);
        userButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {

                super.clicked(event, x, y);

                if (isRegister) {

                    clickedRegister();
                    return;
                }

                clickedLogin();
            }
        });
        userButton.setWidth(72);
        VUtils.centerX(userButton);

        float halfWidth = userButton.getWidth() / 2;
        float increment = -halfWidth - space;

        if (!isRegister) {

            increment = halfWidth + space;
        }

        userButton.moveBy(increment, 0);
    }

    private void clickedLogin() {

    }

    private void clickedRegister() {

    }

    private void setupLogoImage() {

        logoImage = VImage.fromFile(Gdx.files.internal(Constants.LOGIN_BKG_IMAGE));
        logoImage.scaleBy(-0.5f);
        VUtils.centerX(logoImage);
        logoImage.setY(Gdx.graphics.getHeight() - logoImage.getHeight() * logoImage.getScaleY());
        getGuiStage().addActor(logoImage);
    }

    private void setupQuitButton() {

        TextButton quitButton = addButton(Constants.BUTTON_QUIT, 10, 10);
        quitButton.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);

                Gdx.app.exit();
            }
        });
    }

    private TextButton addButton(String text, float x, float y) {

        TextButton button = ButtonFactory.textButton(text, guiFont);
        button.setPosition(x, y);
        getGuiStage().addActor(button);
        return button;
    }
}