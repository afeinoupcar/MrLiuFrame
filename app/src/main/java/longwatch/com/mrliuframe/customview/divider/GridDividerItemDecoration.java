package longwatch.com.mrliuframe.customview.divider;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.DimenRes;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * 会存在无法让item的宽度平分，所以列的最右边还是得画出分割线，控件设置下面三个属性
 * android:paddingBottom="4dp"
 * android:paddingLeft="4dp"
 * android:paddingTop="4dp"
 */
public class GridDividerItemDecoration extends FlexibleDividerDecoration {

    private MarginProvider mMarginProvider;

    protected GridDividerItemDecoration(Builder builder) {
        super(builder);
        mMarginProvider = builder.mMarginProvider;
    }

    @Override
    protected Rect getDividerBound(int position, RecyclerView parent, View child) {
        Rect bounds = new Rect(0, 0, 0, 0);
        int transitionX = (int) ViewCompat.getTranslationX(child);
        int transitionY = (int) ViewCompat.getTranslationY(child);
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
        bounds.top = parent.getPaddingTop() +
                mMarginProvider.dividerTopMargin(position, parent) + transitionY;
        bounds.bottom = parent.getHeight() - parent.getPaddingBottom() -
                mMarginProvider.dividerBottomMargin(position, parent) + transitionY;
        bounds.left = parent.getPaddingLeft() +
                mMarginProvider.dividerLeftMargin(position, parent) + transitionX;
        bounds.right = parent.getWidth() - parent.getPaddingRight() -
                mMarginProvider.dividerRightMargin(position, parent) + transitionX;

        int wDividerSize = getDividerSize(position, parent, true);
        int hDividerSize = getDividerSize(position, parent, false);

        if (mDividerType == DividerType.DRAWABLE) {
            bounds.left = child.getRight() + params.leftMargin + transitionX;
            bounds.right = bounds.left + wDividerSize;
            bounds.top = child.getBottom() + params.topMargin + transitionY;
            bounds.bottom = bounds.top + hDividerSize;
        } else {
            bounds.left = child.getRight() + params.leftMargin + wDividerSize / 2 + transitionX;
            bounds.right = bounds.left;
            bounds.top = child.getBottom() + params.topMargin + hDividerSize / 2 + transitionY;
            bounds.bottom = bounds.top;
        }

        return bounds;
    }

    @Override
    protected void setItemOffsets(Rect outRect, int position, RecyclerView parent) {
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        if (isLastRaw(parent, position, spanCount, childCount)) {// 如果是最后一行，则不需要绘制底部
            outRect.set(0, 0, getDividerSize(position, parent, true), 0);
        } else if (isLastColumn(parent, position, spanCount, childCount)) {// 如果是最后一列，则不需要绘制右边
//            outRect.set(0, 0, 0, getDividerSize(position, parent, false));//TODO
            outRect.set(0, 0, getDividerSize(position, parent, true), getDividerSize(position, parent, false));
        } else {
            outRect.set(0, 0, getDividerSize(position, parent, true), getDividerSize(position, parent, false));
        }
    }

    private int getDividerSize(int position, RecyclerView parent, boolean isWidth) {
        if (mPaintProvider != null) {
            return (int) mPaintProvider.dividerPaint(position, parent).getStrokeWidth();
        } else if (mSizeProvider != null) {
            return mSizeProvider.dividerSize(position, parent);
        } else if (mDrawableProvider != null) {
            Drawable drawable = mDrawableProvider.drawableProvider(position, parent);
//            return drawable.getIntrinsicWidth();
            return isWidth ? drawable.getIntrinsicWidth() : drawable.getIntrinsicHeight();
        }
        throw new RuntimeException("failed to get size");
    }

    /**
     * Interface for controlling divider margin
     */
    public interface MarginProvider {

        /**
         * Returns top margin of divider.
         *
         * @param position Divider position
         * @param parent   RecyclerView
         * @return top margin
         */
        int dividerTopMargin(int position, RecyclerView parent);

        /**
         * Returns bottom margin of divider.
         *
         * @param position Divider position
         * @param parent   RecyclerView
         * @return bottom margin
         */
        int dividerBottomMargin(int position, RecyclerView parent);
		
        /**
         * Returns left margin of divider.
         *
         * @param position Divider position
         * @param parent   RecyclerView
         * @return left margin
         */
        int dividerLeftMargin(int position, RecyclerView parent);

        /**
         * Returns right margin of divider.
         *
         * @param position Divider position
         * @param parent   RecyclerView
         * @return right margin
         */
        int dividerRightMargin(int position, RecyclerView parent);
    }

    public static class Builder extends FlexibleDividerDecoration.Builder<Builder> {

        private MarginProvider mMarginProvider = new MarginProvider() {
            @Override
            public int dividerTopMargin(int position, RecyclerView parent) {
                return 0;
            }

            @Override
            public int dividerBottomMargin(int position, RecyclerView parent) {
                return 0;
            }
			@Override
            public int dividerLeftMargin(int position, RecyclerView parent) {
                return 0;
            }

            @Override
            public int dividerRightMargin(int position, RecyclerView parent) {
                return 0;
            }
        };

        public Builder(Context context) {
            super(context);
        }

        public Builder margin(final int topMargin, final int bottomMargin, final int leftMargin, final int rightMargin) {
            return marginProvider(new MarginProvider() {
                @Override
                public int dividerTopMargin(int position, RecyclerView parent) {
                    return topMargin;
                }

                @Override
                public int dividerBottomMargin(int position, RecyclerView parent) {
                    return bottomMargin;
                }

                @Override
                public int dividerLeftMargin(int position, RecyclerView parent) {
                    return leftMargin;
                }

                @Override
                public int dividerRightMargin(int position, RecyclerView parent) {
                    return rightMargin;
                }
            });
        }

        public Builder margin(int verticalMargin, int horizontalMargin) {
            return margin(verticalMargin, verticalMargin, horizontalMargin, horizontalMargin);
        }

        public Builder marginResId(@DimenRes int topMarginId, @DimenRes int bottomMarginId, @DimenRes int leftMarginId, @DimenRes int rightMarginId) {
            return margin(mResources.getDimensionPixelSize(topMarginId),
                    mResources.getDimensionPixelSize(bottomMarginId),
                    mResources.getDimensionPixelSize(leftMarginId),
                    mResources.getDimensionPixelSize(rightMarginId));
        }

        public Builder marginResId(@DimenRes int verticalMarginId, @DimenRes int horizontalMarginId) {
            return marginResId(verticalMarginId, verticalMarginId, horizontalMarginId, horizontalMarginId);
        }

        public Builder marginProvider(MarginProvider provider) {
            mMarginProvider = provider;
            return this;
        }

        public GridDividerItemDecoration build() {
            checkBuilderParams();
            return new GridDividerItemDecoration(this);
        }
    }

    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager)
                    .getSpanCount();
        }
        return spanCount;
    }

    private boolean isLastColumn(RecyclerView parent, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
            {
                return true;
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                if ((pos + 1) % spanCount == 0)// 如果是最后一列，则不需要绘制右边
                {
                    return true;
                }
            } else {
                childCount = childCount - childCount % spanCount;
                if (pos >= childCount)// 如果是最后一列，则不需要绘制右边
                    return true;
            }
        }
        return false;
    }

    private boolean isLastRaw(RecyclerView parent, int pos, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            childCount = childCount - childCount % spanCount;
            if (pos >= childCount)// 如果是最后一行，则不需要绘制底部
                return true;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int orientation = ((StaggeredGridLayoutManager) layoutManager)
                    .getOrientation();
            // StaggeredGridLayoutManager 且纵向滚动
            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                childCount = childCount - childCount % spanCount;
                // 如果是最后一行，则不需要绘制底部
                if (pos >= childCount)
                    return true;
            } else {// StaggeredGridLayoutManager 且横向滚动
                // 如果是最后一行，则不需要绘制底部
                if ((pos + 1) % spanCount == 0) {
                    return true;
                }
            }
        }
        return false;
    }

}
