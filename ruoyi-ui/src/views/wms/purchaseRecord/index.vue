<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="商品ID" prop="goodsId">
        <el-input
          v-model="queryParams.goodsId"
          placeholder="请输入商品ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品名" prop="goodsName">
        <el-input
          v-model="queryParams.goodsName"
          placeholder="请输入商品名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="规格型号" prop="normsModel">
        <el-input
          v-model="queryParams.normsModel"
          placeholder="请输入规格型号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="单位" prop="unit">
        <el-input
          v-model="queryParams.unit"
          placeholder="请输入单位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="数量" prop="quantity">
        <el-input
          v-model="queryParams.quantity"
          placeholder="请输入数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="单价" prop="price">
        <el-input
          v-model="queryParams.price"
          placeholder="请输入单价"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="供货方" prop="supplier">
        <el-input
          v-model="queryParams.supplier"
          placeholder="请输入供货方"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="采购人" prop="purchaser">
        <el-input
          v-model="queryParams.purchaser"
          placeholder="请输入采购人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用途" prop="useTo">
        <el-input
          v-model="queryParams.useTo"
          placeholder="请输入用途"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="有无发票" prop="hasFapiao">
        <el-input
          v-model="queryParams.hasFapiao"
          placeholder="请输入有无发票"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="相应发票" prop="fapiao">
        <el-input
          v-model="queryParams.fapiao"
          placeholder="请输入相应发票"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品图片" prop="picture">
        <el-input
          v-model="queryParams.picture"
          placeholder="请输入商品图片"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
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
          v-hasPermi="['purchase:purchaseRecord:add']"
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
          v-hasPermi="['purchase:purchaseRecord:edit']"
        >修改</el-button>
      </el-col>
<!--      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['purchase:purchaseRecord:remove']"
        >删除</el-button>
      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['purchase:purchaseRecord:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-upload2"
          size="mini"
          @click="handleImport"
          v-hasPermi="['purchase:purchaseRecord:import']"
        >导入</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="purchaseRecordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="记录ID" align="center" prop="purchaseRecordId" v-if="false"/>
      <el-table-column label="商品ID" align="center" prop="goodsId" v-if="false" />
      <el-table-column label="商品名" align="center" prop="goodsName" />
      <el-table-column label="规格型号" align="center" prop="normsModel" />
      <el-table-column label="单位" align="center" prop="unit" width="50" />
      <el-table-column label="数量" align="center" prop="quantity" width="50"/>
      <el-table-column label="单价" align="center" prop="price" width="50"/>
      <el-table-column label="采购日期时间" align="center" prop="inDate" width="140">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.inDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="类别" align="center" prop="classType" />
      <el-table-column label="供货方" align="center" prop="supplier" />
      <el-table-column label="采购人" align="center" prop="purchaser" />
      <el-table-column label="用途" align="center" prop="useTo" />
      <el-table-column label="有无发票" align="center" prop="hasFapiao" />
      <el-table-column label="相应发票" align="center" prop="fapiao" />
      <el-table-column label="商品图片" align="center" prop="picture" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['purchase:purchaseRecord:edit']"
          >修改</el-button>
<!--          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['purchase:purchaseRecord:remove']"
          >删除</el-button>-->
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

    <!-- 添加或修改采购记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="商品ID" prop="goodsId" v-if="false">
          <el-input v-model="form.goodsId" placeholder="请输入商品ID" />
        </el-form-item>
        <el-form-item label="商品名" prop="goodsName">
          <el-input v-model="form.goodsName" placeholder="请输入商品名" />
        </el-form-item>
        <el-form-item label="规格型号" prop="normsModel">
          <el-input v-model="form.normsModel" placeholder="请输入规格型号" />
        </el-form-item>
        <el-form-item label="单位" prop="unit">
          <el-input v-model="form.unit" placeholder="请输入单位" />
        </el-form-item>
        <el-form-item label="数量" prop="quantity">
          <el-input v-model="form.quantity" placeholder="请输入数量" />
        </el-form-item>
        <el-form-item label="单价" prop="price">
          <el-input v-model="form.price" placeholder="请输入单价" />
        </el-form-item>
        <el-form-item label="供货方" prop="supplier">
          <el-input v-model="form.supplier" placeholder="请输入供货方" />
        </el-form-item>
        <el-form-item label="采购人" prop="purchaser">
          <el-input v-model="form.purchaser" placeholder="请输入采购人" />
        </el-form-item>

        <el-form-item label="用途" prop="useTo">
          <el-input v-model="form.useTo" placeholder="请输入用途" />
        </el-form-item>
        <el-form-item label="有无发票" prop="hasFapiao">
          <el-input v-model="form.hasFapiao" placeholder="请输入有无发票" />
        </el-form-item>
        <el-form-item label="相应发票" prop="fapiao">
          <el-input v-model="form.fapiao" placeholder="请输入相应发票" />
        </el-form-item>
        <el-form-item label="商品图片" prop="picture">
<!--          <el-input v-model="form.picture" placeholder="请输入商品图片" />-->
          <picture :purRecord="purRecord" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 采购单导入对话框 -->
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
            <el-checkbox v-model="upload.updateSupport" /> 是否更新已经存在的采购数据
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
import { listPurchaseRecord, getPurchaseRecord, delPurchaseRecord, addPurchaseRecord, updatePurchaseRecord,importPurchaseRecord } from "@/api/wms/purchaseRecord";
import {getToken} from "@/utils/auth";
import picture from "./picture/picture.vue";

export default {
  name: "PurchaseRecord",
  components: { picture},
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
      // 总条数
      total: 0,
      // 采购记录表格数据
      purchaseRecordList: [],
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
        url: process.env.VUE_APP_BASE_API + "/purchase/purchaseRecord/importData"
      },
      //上传图片所用信息
      purRecord: {},
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        goodsId: null,
        goodsName: null,
        normsModel: null,
        unit: null,
        quantity: null,
        price: null,
        classType: null,
        supplier: null,
        purchaser: null,
        useTo: null,
        hasFapiao: null,
        fapiao: null,
        picture: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        goodsName: [
          { required: true, message: "商品名不能为空", trigger: "blur" }
        ],
        supplier: [
          { required: true, message: "供货方不能为空", trigger: "blur" }
        ],
        purchaser: [
          { required: true, message: "采购人不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询采购记录列表 */
    getList() {
      this.loading = true;
      listPurchaseRecord(this.queryParams).then(response => {
        this.purchaseRecordList = response.rows;
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
        purchaseRecordId: null,
        goodsId: null,
        goodsName: null,
        normsModel: null,
        unit: null,
        quantity: null,
        price: null,
        classType: null,
        supplier: null,
        purchaser: null,
        useTo: null,
        hasFapiao: null,
        fapiao: null,
        picture: null
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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.purchaseRecordId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加采购记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const purchaseRecordId = row.purchaseRecordId || this.ids
      getPurchaseRecord(purchaseRecordId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改采购记录";
      });
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = "抢购买卖导入";
      this.upload.open = true;
      //importPurchaseRecord()
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download('' +
        '/purchaseRecord/importTemplate', {
      }, `template_${new Date().getTime()}.xlsx`)
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
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.purchaseRecordId != null) {
            updatePurchaseRecord(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPurchaseRecord(this.form).then(response => {
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
      const purchaseRecordIds = row.purchaseRecordId || this.ids;
      this.$modal.confirm('是否确认删除采购记录编号为"' + purchaseRecordIds + '"的数据项？').then(function() {
        return delPurchaseRecord(purchaseRecordIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('purchase/purchaseRecord/export', {
        ...this.queryParams
      }, `purchaseRecord_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
