package cn.ucai.live.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hyphenate.easeui.utils.EaseUserUtils;
import com.hyphenate.easeui.widget.EaseImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.live.I;
import cn.ucai.live.LiveHelper;
import cn.ucai.live.R;
import cn.ucai.live.data.model.Gift;

/**
 * Created by wei on 2016/6/7.
 */
@RemoteViews.RemoteView
public class LiveLeftGiftView extends RelativeLayout {
    @BindView(R.id.avatar)
    EaseImageView avatar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.gift_image)
    ImageView giftImage;

    String username;
    @BindView(R.id.gift_text)
    TextView giftText;

    public LiveLeftGiftView(Context context) {
        super(context);
        init(context, null);
    }

    public LiveLeftGiftView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    public LiveLeftGiftView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.widget_left_gift, this);
        ButterKnife.bind(this);
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setAvatar(String username) {
        if (username != null) {
            EaseUserUtils.setAppUserAvatar(getContext(), username, this.avatar);
        } else {

            Glide.with(getContext()).load(avatar).into(this.avatar);
        }
    }

    public ImageView getGiftImageView() {
        return giftImage;
    }

    public void setGift(int id) {
        if (id == 0) {
            giftText.setText("送了一个校园之星");
            giftImage.setImageResource(R.drawable.gift_default);
        } else {
            Gift gift= LiveHelper.getInstance().getAppGiftList().get(id);
            EaseUserUtils.setAppUserAvatarbyPath(getContext(),gift.getGurl(),giftImage, I.GIFT_TYPE);
            giftText.setText("送了一个"+gift.getGname());
        }
    }
}
