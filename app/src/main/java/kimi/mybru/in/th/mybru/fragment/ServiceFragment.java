package kimi.mybru.in.th.mybru.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import kimi.mybru.in.th.mybru.R;
import kimi.mybru.in.th.mybru.utility.FoodAdapter;
import kimi.mybru.in.th.mybru.utility.GetAllData;
import kimi.mybru.in.th.mybru.utility.MyConstant;

public class ServiceFragment extends Fragment {

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create LListView
        createLListView();

    }   // Main Method

    private void createLListView() {
        ListView listView = getView().findViewById(R.id.listViewFood);
        MyConstant myConstant = new MyConstant();

        try {

            GetAllData getAllData = new GetAllData(getActivity());
            getAllData.execute(myConstant.getUrlGetAllFood());

            String jsonString = getAllData.get();

            JSONArray jsonArray = new JSONArray(jsonString);

            String[] foodStrings = new String[jsonArray.length()];
            String[] priceStrings = new String[jsonArray.length()];
            String[] detailStrings = new String[jsonArray.length()];
            String[] imageStrings = new String[jsonArray.length()];

            for (int i=0; i<jsonArray.length(); i+=1) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                foodStrings[i] = jsonObject.getString("NameFood");
                priceStrings[i] = jsonObject.getString("Price");
                detailStrings[i] = jsonObject.getString("Detail");
                imageStrings[i] = jsonObject.getString("ImagePath");

            }

            FoodAdapter foodAdapter = new FoodAdapter(getActivity(),imageStrings,foodStrings,priceStrings,detailStrings);
            listView.setAdapter(foodAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);
        return view;
    }
}
