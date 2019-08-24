package com.uasjsaonandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

public class ListAdapterContact extends BaseAdapter implements
        Filterable {
    private Context context;
    private List<Contact> list, filterd;
    public ListAdapterContact(Context context, List<Contact> list)
    {
        this.context = context;
        this.list = list;
        this.filterd = this.list;
    }
    @Override
    public int getCount() {
        return filterd.size();
    }
    @Override
    public Object getItem(int position) {
        return filterd.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup
            parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(R.layout.list_api,null);
        }
        Contact hp = filterd.get(position);
        TextView textName = (TextView)
                convertView.findViewById(R.id.tv_name);
        textName.setText(String.format("Name : %s", hp.getName()));

        TextView textEmail = (TextView)
                convertView.findViewById(R.id.tv_email);
        textEmail.setText(String.format("Email : %s", hp.getEmail()));

        TextView textAddress = (TextView)
                convertView.findViewById(R.id.tv_address);
        textAddress.setText(String.format("Address : %s", hp.getAddress()));

        TextView textGender = (TextView)
                convertView.findViewById(R.id.tv_gender);
        textGender.setText(String.format("Gender : %s", hp.getGender()));

        TextView textMobile = (TextView)
                convertView.findViewById(R.id.tv_mobile);
        textMobile.setText("Mobile : "+hp.getMobile());

        TextView textHome = (TextView)
               convertView.findViewById(R.id.tv_home);
        textHome.setText("Home : "+hp.getHome());

        TextView textOffice = (TextView)
            convertView.findViewById(R.id.tv_office);
        textOffice.setText("Office : " + hp.getOffice());
        return convertView;
    }
    @Override
    public Filter getFilter() {
        HandphoneFilter filter = new HandphoneFilter();
        return filter;
    }
    /**
     * Class filter untuk melakukan filter (pencarian)
     */
    private class HandphoneFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence
                                                         constraint) {
            List<Contact> filteredData = new ArrayList<Contact>();
            FilterResults result = new FilterResults();
            String filterString = constraint.toString().toLowerCase();
            for (Contact hp : list) {
                if (hp.getName().toLowerCase().contains(filterString)) {
                    filteredData.add(hp);
                }
            }
            result.count = filteredData.size();
            result.values = filteredData;
            return result;
        }
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            filterd = (List<Contact>) results.values;
            notifyDataSetChanged();
        }
    }
}