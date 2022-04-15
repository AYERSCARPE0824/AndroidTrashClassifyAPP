package com.car.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.car.R;
import com.car.client.SocketClient;
import com.car.information.NecessaryInformation;
import com.car.request.DriverRequest;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InformationFragment extends Fragment {
    private TextView tv_driver_id=null;
    private EditText ed_repwd=null;
    private Button btn_submit;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public InformationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InformationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InformationFragment newInstance(String param1, String param2) {
        InformationFragment fragment = new InformationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_information, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv_driver_id = (TextView)getActivity().findViewById(R.id.tv_driver_id);
//        tv_driver_id.setText(NecessaryInformation.driverResult.getUsername());

        tv_driver_id.setText("01000251");

        btn_submit = (Button)getActivity().findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_repwd = getActivity().findViewById(R.id.ed_repwd);
                DriverRequest request = new DriverRequest();
                request.setUsername(NecessaryInformation.driverResult.getUsername());
                request.setPassword(ed_repwd.getText().toString());
                request.setRequestType(DriverRequest.EDIT_REQUEST);
                SocketClient socketClient = new SocketClient(request);
                socketClient.start();
                try {
                    new Thread().sleep(1000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                if(NecessaryInformation.driverStatus == false|| NecessaryInformation.driverResult==null){
                    Toast.makeText(getActivity(),"网络错误",Toast.LENGTH_SHORT).show();
                }
                else if(NecessaryInformation.driverResult.isRes())
                {
                    Toast.makeText(getActivity(),"修改成功",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(),"修改失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}