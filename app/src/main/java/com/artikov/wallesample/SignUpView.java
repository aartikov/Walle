package com.artikov.wallesample;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.artikov.walle.FormValidationResult;

/**
 * Date: 12/11/2016
 * Time: 16:45
 *
 * @author Artur Artikov
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface SignUpView extends MvpView {
    void setValidationResult(FormValidationResult result);
    @StateStrategyType(SkipStrategy.class)
    void showSignedUpMessage();
}
