<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="物品名称" prop="goodsName">
        <el-input
          v-model="queryParams.goodsName"
          placeholder="请输入物品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="规格" prop="specs">
        <el-input
          v-model="queryParams.specs"
          placeholder="请输入规格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="颜色" prop="color" v-show="showMore">
        <el-input
          v-model="queryParams.color"
          placeholder="请输入颜色"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="采购人" prop="purchasePeople" v-show="showMore">
        <el-input
          v-model="queryParams.purchasePeople"
          placeholder="请输入采购人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="购入人" prop="inPeople" v-show="showMore">
        <el-input
          v-model="queryParams.inPeople"
          placeholder="请输入购入人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="结算人" prop="outPeople" v-show="showMore">
        <el-input
          v-model="queryParams.outPeople"
          placeholder="请输入结算人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="购入结算方式" prop="inSettleModel" v-show="showMore">
        <el-input
          v-model="queryParams.inSettleModel"
          placeholder="请输入购入结算方式"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="支出结算方式" prop="outSettleModel" v-show="showMore">
        <el-input
          v-model="queryParams.outSettleModel"
          placeholder="请输入支出结算方式"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="采购时间" prop="buyTime" v-show="showMore">
        <el-date-picker clearable
          v-model="queryParams.buyTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择采购时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="转入时间" prop="inTime" v-show="showMore">
        <el-date-picker clearable
          v-model="queryParams.inTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择转入时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="转出时间" prop="outTime" v-show="showMore">
        <el-date-picker clearable
          v-model="queryParams.outTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择转出时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="购入结算时间" prop="outMomeyTime" v-show="showMore">
        <el-date-picker clearable
          v-model="queryParams.outMomeyTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择购入结算时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="出手结算时间" prop="inMomeyTime" v-show="showMore">
        <el-date-picker clearable
          v-model="queryParams.inMomeyTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择出手结算时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="购入渠道" prop="buyFrom" v-show="showMore">
        <el-input
          v-model="queryParams.buyFrom"
          placeholder="请输入购入渠道"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="showMoreC">更多条件</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:sale:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:sale:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:sale:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
          v-hasPermi="['system:sale:import']"
        >导入</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:sale:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-hidden"
          size="mini"
          @click="hiddenClom"
        >隐藏列</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="saleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="抢购买卖ID" align="center" prop="proRushSaleId" v-if="hiddenFlag"/>
      <el-table-column label="物品名称" align="center" prop="goodsName">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pro_rushsale_thing" :value="scope.row.goodsName"/>
        </template>>
      </el-table-column>
      <el-table-column label="规格" align="center" prop="specs" />
      <el-table-column label="收入结算价格" align="center" prop="inPrice" v-if="columns[2].visible"/>
      <el-table-column label="支出结算价格" align="center" prop="outPrice" v-if="columns[3].visible"/>
      <el-table-column label="采购人" align="center" prop="purchasePeople" v-if="columns[4].visible"/>
      <el-table-column label="结算人" align="center" prop="outPeople" v-if="columns[6].visible"/>
      <el-table-column label="采购价格" align="center" prop="purchasePrice" v-if="columns[1].visible"/>
      <el-table-column label="支出结算时间" align="center" prop="outMomeyTime" width="180" v-if="columns[12].visible">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.outMomeyTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="收入结算时间" align="center" prop="inMomeyTime" width="180" v-if="columns[13].visible">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.inMomeyTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="购入渠道" align="center" prop="buyFrom" v-if="columns[14].visible">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.pro_rushsale_buyfrom" :value="scope.row.buyFrom"/>
        </template>>
      </el-table-column>

      <el-table-column label="数量" align="center" prop="quantity" v-if="columns[15].visible"/>
      <el-table-column label="购入人" align="center" prop="inPeople" v-if="columns[5].visible"/>
      <el-table-column label="收入结算方式" align="center" prop="inSettleModel" v-if="columns[7].visible"/>
      <el-table-column label="支出结算方式" align="center" prop="outSettleModel" v-if="columns[8].visible"/>
      <el-table-column label="采购时间" align="center" prop="buyTime" width="180" v-if="columns[9].visible">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.buyTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="转入时间" align="center" prop="inTime" width="180" v-if="columns[10].visible">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.inTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="转出时间" align="center" prop="outTime" width="180" v-if="columns[11].visible">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.outTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="物品图片" align="center" prop="picture" v-if="hiddenFlag"/>
      <el-table-column label="颜色" align="center" prop="color" v-if="columns[0].visible"/>
      <el-table-column label="备注" align="center" prop="remark" v-if="columns[16].visible"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:sale:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:sale:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改抢购买卖对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="物品名称" prop="goodsName">
          <el-select v-model="form.goodsName" placeholder="请选择购入渠道">
            <el-option
              v-for="dict in dict.type.pro_rushsale_thing"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
<!--          <el-input v-model="form.goodsName" placeholder="请输入物品名称" />-->
        </el-form-item>
        <el-form-item label="规格" prop="specs">
          <el-input v-model="form.specs" placeholder="请输入规格" />
        </el-form-item>
        <el-form-item label="采购价格" prop="purchasePrice">
          <el-input v-model="form.purchasePrice" placeholder="请输入采购价格" />
        </el-form-item>
        <el-form-item label="购入渠道" prop="buyFrom">
          <el-select v-model="form.buyFrom" placeholder="请选择购入渠道">
            <el-option
              v-for="dict in dict.type.pro_rushsale_buyfrom"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="数量" prop="quantity">
          <el-input v-model="form.quantity" placeholder="请输入数量" />
        </el-form-item>
        <el-form-item label="采购人" prop="purchasePeople">
         <el-input v-model="form.purchasePeople" placeholder="请输入采购人" />
        </el-form-item>

        <el-form-item label="采购时间" prop="buyTime">
          <el-date-picker clearable
                          v-model="form.buyTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择采购时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结算人" prop="outPeople">
          <el-input v-model="form.outPeople" placeholder="请输入结算人" />
        </el-form-item>

        <el-form-item label="转入时间" prop="inTime">
          <el-date-picker clearable
                          v-model="form.inTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择转入时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="转出时间" prop="outTime">
          <el-date-picker clearable
                          v-model="form.outTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择转出时间">
          </el-date-picker>
        </el-form-item>

        <el-form-item label="收入结算价格" prop="inPrice">
          <el-input v-model="form.inPrice" placeholder="请输入收入结算价格" />
        </el-form-item>
        <el-form-item label="收入结算方式" prop="inSettleModel">
          <el-input v-model="form.inSettleModel" placeholder="请输入收入结算方式" />
        </el-form-item>
        <el-form-item label="收入结算时间" prop="inMomeyTime">
          <el-date-picker clearable
                          v-model="form.inMomeyTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择收入结算时间">
          </el-date-picker>
        </el-form-item>

        <el-form-item label="支出结算价格" prop="outPrice">
          <el-input v-model="form.outPrice" placeholder="请输入支出结算价格" />
        </el-form-item>
        <el-form-item label="支出结算方式" prop="outSettleModel">
          <el-input v-model="form.outSettleModel" placeholder="请输入支出结算方式" />
        </el-form-item>
        <el-form-item label="支出结算时间" prop="outMomeyTime">
          <el-date-picker clearable
                          v-model="form.outMomeyTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择支出结算时间">
          </el-date-picker>
        </el-form-item>

        <el-form-item label="购入人" prop="inPeople" v-if="false">
          <el-input v-model="form.inPeople" placeholder="请输入购入人" />
        </el-form-item>
        <el-form-item label="物品图片" prop="picture">
          <el-input v-model="form.picture" placeholder="请输入物品图片" />
        </el-form-item>
        <el-form-item label="颜色" prop="color">
          <el-input v-model="form.color" placeholder="请输入颜色" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 用户导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url + '?updateSupport=' + upload.updateSupport"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip text-center" slot="tip">
          <div class="el-upload__tip" slot="tip">
            <el-checkbox v-model="upload.updateSupport" /> 是否更新已经存在的用户数据
          </div>
          <span>仅允许导入xls、xlsx格式文件。</span>
          <el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;" @click="importTemplate">下载模板</el-link>
        </div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listSale, getSale, delSale, addSale, updateSale } from "@/api/rushsale/sale";
import {getToken} from "@/utils/auth";
import {listDept} from "@/api/system/dept";

export default {
  name: "Sale",
  dicts: ['pro_rushsale_buyfrom','pro_rushsale_thing'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      //更多更少条件
      showMore: false,
      //更多更少条件
      hiddenFlag: false,
      // 总条数
      total: 0,
      // 抢购买卖表格数据
      saleList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 用户导入参数
      upload: {
        // 是否显示弹出层（导入）
        open: false,
        // 弹出层标题（导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的用户数据0:更新
        updateSupport: 1,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/system/sale/importRushData"
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        goodsName: null,
        specs: null,
        picture: null,
        color: null,
        purchasePrice: null,
        inPrice: null,
        outPrice: null,
        purchasePeople: null,
        inPeople: null,
        outPeople: null,
        inSettleModel: null,
        outSettleModel: null,
        buyTime: null,
        inTime: null,
        outTime: null,
        outMomeyTime: null,
        inMomeyTime: null,
        buyFrom: null,
        quantity: null,
      },
      // 列信息
      columns: [
        { key: 0, label: '颜色', visible: true },
        { key: 1, label: '采购价格', visible: true },
        { key: 2, label: '购入价格', visible: true },
        { key: 3, label: '结算价格', visible: true },
        { key: 4, label: '采购人', visible: true },
        { key: 5, label: '购入人', visible: true },
        { key: 6, label: '结算人', visible: true },
        { key: 7, label: '购入结算方式', visible: true },
        { key: 8, label: '支出结算方式', visible: true },
        { key: 9, label: '采购时间', visible: true },
        { key: 10, label: '转入时间', visible: true },
        { key: 11, label: '转出时间', visible: true },
        { key: 12, label: '购入结算时间', visible: true },
        { key: 13, label: '出手结算时间', visible: true },
        { key: 14, label: '购入渠道', visible: true },
        { key: 15, label: '数量', visible: true },
        { key: 16, label: '备注', visible: true }
      ],
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询抢购买卖列表 */
    getList() {
      this.loading = true;
      listSale(this.queryParams).then(response => {
        this.saleList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        proRushSaleId: null,
        goodsName: null,
        specs: null,
        picture: null,
        color: null,
        purchasePrice: null,
        inPrice: null,
        outPrice: null,
        purchasePeople: null,
        inPeople: null,
        outPeople: null,
        inSettleModel: null,
        outSettleModel: null,
        buyTime: null,
        inTime: null,
        outTime: null,
        outMomeyTime: null,
        inMomeyTime: null,
        buyFrom: null,
        quantity: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },

    showMoreC(e){
      if(this.showMore){
        this.showMore=false;
        e.target.innerText="更多条件";
      }else{
        this.showMore=true;
        e.target.innerText="隐藏条件";
      }
    },

    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.proRushSaleId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加抢购买卖";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const proRushSaleId = row.proRushSaleId || this.ids
      getSale(proRushSaleId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改抢购买卖";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.proRushSaleId != null) {
            updateSale(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSale(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const proRushSaleIds = row.proRushSaleId || this.ids;
      this.$modal.confirm('是否确认删除抢购买卖编号为"' + proRushSaleIds + '"的数据项？').then(function() {
        return delSale(proRushSaleIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/sale/export', {
        ...this.queryParams
      }, `sale_${new Date().getTime()}.xlsx`)
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = "抢购买卖导入";
      this.upload.open = true;
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download('system/sale/importTemplate', {
      }, `rushSale_template_${new Date().getTime()}.xlsx`)
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "导入结果", { dangerouslyUseHTMLString: true });
      this.getList();
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    },
    /** 隐藏列 */
    hiddenClom(e){
      if(this.hiddenFlag){
        this.hiddenFlag=false;
        e.target.innerText="展示列";
      }else{
        this.hiddenFlag=true;
        e.target.innerText="隐藏列";
      }
    }
  }
};
</script>
