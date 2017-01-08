package aviadapps.getfood;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import aviadapps.getfood.R;

public class companyFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        LinearLayout linearLayout = new LinearLayout(getActivity());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL); //or VERTICAL

        ViewGroup.LayoutParams buttonParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Button button = new Button(getActivity());
        button.setLayoutParams(buttonParams);

        Button button2 = new Button(getActivity());
        button2.setLayoutParams(buttonParams);

        linearLayout.addView(button);
        linearLayout.addView(button2);

        //like this, add all buttons and other views
        //you can use a loop for adding multiple similar views

        container.addView(linearLayout);
        View view = inflater.inflate(R.layout.fragment_company, container, true);
        return view;
    }
}
