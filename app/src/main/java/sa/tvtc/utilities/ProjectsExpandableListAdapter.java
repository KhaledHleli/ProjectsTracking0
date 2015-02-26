package sa.tvtc.utilities;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import sa.tvtc.projectstracking.R;

public class ProjectsExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader;
    // header titles child data in format of header title, child title
    private HashMap<String, ProjectFieldsForExpandableList> _listDataChild;

    public ProjectsExpandableListAdapter(Context context, List<String> listDataHeader,
                                         HashMap<String, ProjectFieldsForExpandableList> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition));
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final ProjectFieldsForExpandableList projectFieldsForExpandableList =
                (ProjectFieldsForExpandableList) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.home_page_lv_item, null);
        }

        TextView contractNumberTextValue = (TextView) convertView
                .findViewById(R.id.contractNumberTextValue);
        TextView recipientAdministration    = (TextView) convertView
                .findViewById(R.id.recipientAdministration);
        TextView contractStatus = (TextView) convertView
                .findViewById(R.id.contractStatus);
        TextView trackingEndDate = (TextView) convertView
                .findViewById(R.id.trackingEndDate);

        contractNumberTextValue.setText(projectFieldsForExpandableList.getProjectNumber());
        contractStatus.setText(projectFieldsForExpandableList.getProjectStatus());
        recipientAdministration.setText(projectFieldsForExpandableList.getRecipientAdministration());
        trackingEndDate.setText(DateFormat.getDateInstance
                (DateFormat.LONG, _context.getResources().getConfiguration().locale)
                .format(projectFieldsForExpandableList.getFollowUpDate()));
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.home_page_lv_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}