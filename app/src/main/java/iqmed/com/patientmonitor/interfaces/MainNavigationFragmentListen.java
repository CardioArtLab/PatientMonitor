package iqmed.com.patientmonitor.interfaces;

import android.support.v7.widget.Toolbar;

public interface MainNavigationFragmentListen {
    void gotoFragmentByMenuId(int menuId);
    void initToolbar(Toolbar toolbar);
}
