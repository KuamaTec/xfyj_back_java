package com.zgds.xfyj.service;

import com.zgds.xfyj.dao.AddressMapper;
import com.zgds.xfyj.domain.pojo.Address;
import com.zgds.xfyj.util.ServerResponse;
import com.zgds.xfyj.util.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.thymeleaf.util.ListUtils;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class AddressService {
    private final Logger logger = LoggerFactory.getLogger(AddressService.class);

    @Autowired
    private AddressMapper addressMapper;

    @Transactional(rollbackFor = Exception.class)
    public ServerResponse setDefaultAddress(String userId, String addressId) {
        ServerResponse serverResponse = null;

        try {
            addressMapper.updateIsDefault0(userId);
            addressMapper.updateIsDefault1ByIdUserId(addressId, userId);
            serverResponse = ServerResponse.createBySuccess("设置默认收货地址成功");
            log.info("用户{}设置默认收货地址{}成功", userId, addressId);
        } catch (Exception e) {
            e.printStackTrace();
            serverResponse = ServerResponse.createByErrorMessage("设置用户默认收货地址信息异常");
            log.info("更新用户{}默认收货地址{}信息异常,错误信息{}", userId, addressId, e.getLocalizedMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } finally {
            return serverResponse;
        }
    }

    public ServerResponse getAddress(String userId) {

        ServerResponse serverResponse = null;
        List<Address> list = null;

        try {
            list = addressMapper.getAddressByUserId(userId);
            serverResponse = ServerResponse.createBySuccess("获取用户收货地址信息成功", list);
            log.info("获取用户{}收货地址信息成功", userId);
        } catch (Exception e) {
            e.printStackTrace();
            serverResponse = ServerResponse.createByErrorMessage("获取用户收货地址信息失败");
            log.info("获取用户{}收货地址信息失败,错误信息{}", userId, e.getLocalizedMessage());
        } finally {
            return serverResponse;
        }
    }

    public ServerResponse getAddressByAddrId(String userId,String addId) {

        ServerResponse serverResponse = null;

        try {
            Address address = addressMapper.getByUserIdAddrId(userId, addId);
            serverResponse = ServerResponse.createBySuccess("获取用户收货地址详情成功", address);
            log.info("获取用户{}收货地址详情成功", userId);
        } catch (Exception e) {
            e.printStackTrace();
            serverResponse = ServerResponse.createByErrorMessage("获取用户收货地址详情失败");
            log.info("获取用户{}收货地址详情失败,错误信息{}", userId, e.getLocalizedMessage());
        } finally {
            return serverResponse;
        }
    }

    public ServerResponse delAddress(String userId, String addr_id) {

        ServerResponse serverResponse = null;
        Address address = null;

        try {
            Example example = new Example(Address.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("user_id", userId);
            criteria.andEqualTo("id", addr_id);
            int i = addressMapper.deleteByExample(example);
            if (i > 0) {
                //判断数据库中当前用户的收货地址，如果仅剩1条，置为默认
                Example example1 = new Example(Address.class);
                example1.createCriteria().andEqualTo("user_id",userId);
                List<Address> list = addressMapper.selectByExample(example1);
                if(list.size()==1){
                    Address address1 = list.get(0);
                    address1.setIs_default(Integer.valueOf(1));
                    addressMapper.updateByPrimaryKey(address1);
                }
                serverResponse = ServerResponse.createBySuccess("删除用户收货地址信息成功", address);
                log.info("删除用户{}收货地址信息成功", userId);
            } else {
                serverResponse = ServerResponse.createByErrorMessage("删除用户收货地址信息失败");
                log.info("删除用户{}收货地址信息失败", userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            serverResponse = ServerResponse.createByErrorMessage("删除用户收货地址信息异常");
            log.info("删除用户{}收货地址信息异常,错误信息{}", userId, e.getLocalizedMessage());
        } finally {
            return serverResponse;
        }
    }

    public ServerResponse add(String recieve_name,
                              String detail_address,
                              String mobile1,
                              String mobile2,
                              String province,
                              String city,
                              String region,
                              String user_id) {

        ServerResponse serverResponse = null;

        try {
            //1、构建一个收货地址对象
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Address address = Address.builder()
                    .id(UUIDUtils.generateId())
                    .recieve_name(recieve_name)
                    .detail_address(detail_address)
                    .mobile1(mobile1)
                    .mobile2(mobile2)
                    .province(province)
                    .city(city)
                    .region(region)
                    .is_default(0)
                    .user_id(user_id)
                    .update_time(sdf.format(new Date()))
                    .build();

            //2、当前用户收货地址是否已存在
            Example example = new Example(Address.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("user_id", user_id);

            List<Address> list = addressMapper.selectByExample(example);
            if (ListUtils.isEmpty(list)) {
                address.setIs_default(1);
            }


            int i = addressMapper.insert(address);
            if (i == 1) {
                serverResponse = ServerResponse.createBySuccess("添加收货地址信息成功", address);
                log.info("添加收货地址信息成功");
            } else {
                serverResponse = ServerResponse.createBySuccess("添加收货地址信息失败");
                log.info("添加收货地址信息失败，用户id：{}", address.getUser_id());
            }
        } catch (Exception e) {
            e.printStackTrace();
            serverResponse = ServerResponse.createByErrorMessage("添加收货地址信息失败");
            log.info("添加收货地址信息异常！用户id：{}，错误信息：", user_id, e.getLocalizedMessage());
        } finally {
            return serverResponse;
        }

    }

    public ServerResponse update(String addr_id,
                                 String recieve_name,
                                 String detail_address,
                                 String mobile1,
                                 String mobile2,
                                 String province,
                                 String city,
                                 String region,
                                 String user_id) {

        ServerResponse serverResponse = null;

        try {
            //0、获取
            Address address1 = addressMapper.selectByPrimaryKey(addr_id);
            //1、构建一个收货地址对象
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Address address = Address.builder()
                    .id(addr_id)
                    .recieve_name(recieve_name)
                    .detail_address(detail_address)
                    .mobile1(mobile1)
                    .mobile2(mobile2)
                    .province(province)
                    .city(city)
                    .region(region)
                    .is_default(address1.getIs_default())
                    .user_id(user_id)
                    .update_time(sdf.format(new Date()))
                    .build();

            //2、更行收货地址
            int i = addressMapper.updateByPrimaryKey(address);

            if (i == 1) {
                serverResponse = ServerResponse.createBySuccess("更新收货地址信息成功", address);
                log.info("用户{}更新收货地址{}信息成功", user_id, addr_id);
            } else {
                serverResponse = ServerResponse.createBySuccess("更新收货地址信息失败");
                log.info("用户{} 更新收货地址{}信息失败！", user_id, addr_id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            serverResponse = ServerResponse.createByErrorMessage("更新收货地址信息失败");
            log.info("用户{} 更新收货地址{}信息失败！", user_id, addr_id);
        } finally {
            return serverResponse;
        }

    }

    public ServerResponse edit(String id,
                               String recieve_name,
                               String detail_address,
                               String mobile1,
                               String mobile2,
                               String province,
                               String city,
                               String region,
                               String user_id) {

        ServerResponse serverResponse = null;

        try {
            //1、构建一个收货地址对象
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Address address = Address.builder()
                    .id(id)
                    .recieve_name(recieve_name)
                    .detail_address(detail_address)
                    .mobile1(mobile1)
                    .mobile2(mobile2)
                    .province(province)
                    .city(city)
                    .region(region)
                    .is_default(0)
                    .user_id(user_id)
                    .update_time(sdf.format(new Date()))
                    .build();

            //2、当前用户收货地址是否已存在
            address.setId(UUIDUtils.generateId());
            int i = addressMapper.updateByPrimaryKey(address);
            if (i == 1) {
                serverResponse = ServerResponse.createBySuccess("更新收货地址信息成功", address);
                log.info("更新收货地址信息成功");
            } else {
                serverResponse = ServerResponse.createBySuccess("更新收货地址信息失败");
                log.info("更新收货地址信息失败，用户id：{}", address.getUser_id());
            }
        } catch (Exception e) {
            e.printStackTrace();
            serverResponse = ServerResponse.createByErrorMessage("更新收货地址信息失败");
            log.info("更新收货地址信息异常！用户id：{}，错误信息：", user_id, e.getLocalizedMessage());
        } finally {
            return serverResponse;
        }

    }


}



